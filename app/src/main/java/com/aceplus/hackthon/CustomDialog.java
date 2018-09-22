package com.aceplus.hackthon;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aceplus.shared.Util.Utils;
import com.aceplus.shared.VO.OrderItemVO;
import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class CustomDialog extends Dialog {

    private final Long itemTotal;
    private final Long itemPrice;
    private final UserVO userVO;
    private final String itemId;
    private final String itemName;
    @BindView(R.id.txt_item_name)
    TextView txtItemName;
    @BindView(R.id.txt_itemAmount)
    TextView txtItemAmount;
    @BindView(R.id.txtDescription)
    EditText remarkText;
    int itemAmount = 0;
    public Activity context;
    public Button btnConfirm;


    public CustomDialog(Activity a, Long total, UserVO itemVO, String itemId, String itemName, Long itemPrice) {
        super(a);
        this.context = a;
        this.itemTotal = total;
        this.userVO = itemVO;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        txtItemAmount.setText("0");
        txtItemName.setText(itemName);


    }

    private OrderItemVO createOrderObject(){
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setCustomerDepartment(userVO.getUserDepartment());
        orderItemVO.setCustomerName(userVO.getUserName());
        orderItemVO.setCustomerId(userVO.getUserId());
        orderItemVO.setItemId(itemId);
        orderItemVO.setItemName(itemName);
        orderItemVO.setItemPrice(String.valueOf(itemPrice));
        orderItemVO.setCustomerRemark(remarkText.getText().toString());
        orderItemVO.setItemCount(String.valueOf(itemAmount));
        return orderItemVO;
    }

    @OnClick(R.id.img_close)
    public void closeDialog() {
        dismiss();
    }

    @OnClick(R.id.txtPlus)
    public void doIncrease() {

        if (itemAmount < itemTotal)
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

    @OnClick(R.id.btn_confirm)
    public void doOrder() {
        BackendModel.Companion.getInstance().addTodayNormalOrder(Utils.Companion.getTodayDateNode(), createOrderObject(), new ModelCallback.AddOrderCallback() {
            @Override
            public void addOrderSucceed(@NotNull String message) {
                dismiss();
            }

            @Override
            public void addOrderFailed(@NotNull String message) {
            }
        });
    }

}
