package com.aceplus.hackthon.today_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aceplus.hackthon.R;
import com.aceplus.hackthon.adapter.TodayMenuRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class TodayMenuActivity extends AppCompatActivity {

    @BindView(R.id.todayMenu_rcv)
    RecyclerView rcv_TodayMenu;
    TodayMenuRecyclerViewAdapter todayMenuRecyclerViewAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_menu);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setUpRcv_TodayMenu();
    }

    @OnClick(R.id.imgBack)
    public void goBack() {
        onBackPressed();
    }

    private void setUpRcv_TodayMenu() {
        todayMenuRecyclerViewAdapter = new TodayMenuRecyclerViewAdapter(this, TodayMenuActivity.this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcv_TodayMenu.setLayoutManager(mLayoutManager);
        rcv_TodayMenu.setAdapter(todayMenuRecyclerViewAdapter);
    }
}
