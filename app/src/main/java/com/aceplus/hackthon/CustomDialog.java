package com.aceplus.hackthon;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    @BindView(R.id.txt_itemAmount)
    TextView txtItemAmount;
    int itemAmount = 0;
    public Activity context;
    public Dialog d;
    public Button btnConfirm;

    public CustomDialog(Activity a) {
        super(a);
        this.context = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        txtItemAmount.setText("0");

    }

    @OnClick(R.id.img_close)
    public void closeDialog() {
        dismiss();
    }

    @OnClick(R.id.txtPlus)
    public void doIncrease() {

        if (itemAmount >= 0)
            ++itemAmount;
        txtItemAmount.setText(String.valueOf(itemAmount));
    }

    @OnClick(R.id.txtMinus)
    public void doDecrease() {
        if (itemAmount > 0) {
            --itemAmount;
            txtItemAmount.setText(String.valueOf(itemAmount));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                context.finish();
                break;
            default:
                break;
        }
        dismiss();
    }
}
