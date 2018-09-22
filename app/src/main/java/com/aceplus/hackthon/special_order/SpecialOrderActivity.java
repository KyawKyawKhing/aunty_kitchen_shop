package com.aceplus.hackthon.special_order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.aceplus.hackthon.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class SpecialOrderActivity extends AppCompatActivity {

    @BindView(R.id.sp_menuItem)
    Spinner spMenuItem;
    @BindView(R.id.sp_amount)
    Spinner spAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_order);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setUpMenuItemSpinner();
        setUpAmountSpinner();
    }

    @OnClick(R.id.imgBack)
    public void goBack() {
        onBackPressed();
    }

    private void setUpMenuItemSpinner(){

        List<String> list = new ArrayList<String>();
        list.add("HVC");
        list.add("Local");
        list.add("TAT");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_spinner1,
                list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMenuItem.setAdapter(dataAdapter);
    }
    private void setUpAmountSpinner(){

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
    }
}
