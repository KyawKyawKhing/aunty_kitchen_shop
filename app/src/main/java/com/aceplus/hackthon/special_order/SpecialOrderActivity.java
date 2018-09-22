package com.aceplus.hackthon.special_order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.aceplus.hackthon.R;
import com.aceplus.shared.VO.AvailableItemVO;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class SpecialOrderActivity extends AppCompatActivity implements SpecialOrderContract.View {

    @BindView(R.id.sp_menuItem)
    Spinner spMenuItem;
    @BindView(R.id.sp_amount)
    Spinner spAmount;
    SpecialOrderContract.Presenter presenter;
    FirebaseAuth firebaseAuth;
    private List<AvailableItemVO> orderList;
    @BindView(R.id.tv_itemPrice)
    TextView tvItemPrice;
    private String itemAmount;
    private Long itemPrice;
    private String itemName;
    private String itemId;
    @BindView(R.id.txtDescription1)
    TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_order);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        presenter = new SpecialOrderPresenter(this);
        presenter.displaySpecialAllItem();
        setUpAmountSpinner();
    }

    @OnClick(R.id.imgBack)
    public void goBack() {
        onBackPressed();
    }

    private void setUpMenuItemSpinner(final List<AvailableItemVO> orderList) {
        this.orderList = orderList;
        List<String> menuName = new ArrayList();

        for (int i = 0; i < orderList.size(); i++) {
            menuName.add(orderList.get(i).getItemName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner1,
                menuName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMenuItem.setAdapter(dataAdapter);
        spMenuItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tvItemPrice.setText(String.valueOf(orderList.get(i).getItemPrice()));

                itemName = orderList.get(i).getItemName();
                itemId = orderList.get(i).getItemId();
                itemPrice = orderList.get(i).getItemPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpAmountSpinner() {

        List<Integer> number = new ArrayList<Integer>();
        number.add(1);
        number.add(2);
        number.add(3);
        number.add(4);
        number.add(5);
        number.add(6);
        number.add(7);
        number.add(8);
        number.add(9);
        number.add(10);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                R.layout.custom_spinner1,
                number);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spAmount.setAdapter(dataAdapter);
        spAmount.setSelection(0);
        itemAmount = spAmount.getSelectedItem().toString();
    }

    @Override
    public void showTodayNormalOrder(List<AvailableItemVO> orderList) {
        setUpMenuItemSpinner(orderList);
    }

    @Override
    public void showSuccessfulOrder() {
       finish();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick(R.id.btn_order)
    public void specialOrder() {

        presenter.addSpecialOrder(itemName, itemId, String.valueOf(itemPrice), txtDescription.getText().toString(), itemAmount);


    }
}
