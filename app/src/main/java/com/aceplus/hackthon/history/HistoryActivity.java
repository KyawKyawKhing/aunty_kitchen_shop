package com.aceplus.hackthon.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aceplus.hackthon.R;
import com.aceplus.hackthon.adapter.HistoryListAdapter;
import com.aceplus.shared.VO.OrderItemVO;
import com.google.firebase.auth.FirebaseAuth;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class HistoryActivity extends AppCompatActivity implements HistoryContract.View, DatePickerDialog.OnDateSetListener {


    @BindView(R.id.progressLoading)
    LinearLayout progressLoading;
    @BindView(R.id.rcv_history)
    RecyclerView recyclerView;
    @BindView(R.id.txtDate)
    TextView txtDate;
    private LinearLayoutManager mLayoutManager;
    private HistoryListAdapter historyListAdapter;
    private HistoryPresenter historyPresenter;
    private DatePickerDialog mDatePicker;
    private DatePickerDialog picker;
    FirebaseAuth firebaseAuth;
    private Calendar now;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        now = Calendar.getInstance();
        historyPresenter = new HistoryPresenter(this);
        historyPresenter.displayAllOrderByUser(firebaseAuth.getCurrentUser().getUid());
        setUpReportRecyclerView();
    }

    private void setUpReportRecyclerView() {

        historyListAdapter = new HistoryListAdapter(this, HistoryActivity.this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(historyListAdapter);
    }

    @Override
    public void showOrderList(List<OrderItemVO> orderList) {
        historyListAdapter.setReportList(orderList);
    }

    @Override
    public void showOrderListByDate(List<OrderItemVO> orderList) {
        historyListAdapter.setReportList(orderList);
    }

    @Override
    public void showLoading(boolean active) {
        if (active) {
            progressLoading.setVisibility(View.VISIBLE);
        } else {
            progressLoading.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.calendarLayout)
    public void openCalendar() {

        showDateAlert();
    }

    private void showDateAlert() {

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                HistoryActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );

        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                String searchDate = String.valueOf(dayOfMonth) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
                txtDate.setText(date);
                historyPresenter.displayOrderByDateByUser(searchDate, firebaseAuth.getCurrentUser().getUid());
            }
        });
        dpd.show(getFragmentManager(), "Datepickerdialog");


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }

    @OnClick(R.id.imgBack)
    public void goBack() {

        onBackPressed();
    }
}
