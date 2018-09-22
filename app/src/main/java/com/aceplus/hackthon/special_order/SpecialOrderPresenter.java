package com.aceplus.hackthon.special_order;

import com.aceplus.shared.Util.Utils;
import com.aceplus.shared.VO.AvailableItemVO;
import com.aceplus.shared.VO.OrderItemVO;
import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class SpecialOrderPresenter implements SpecialOrderContract.Presenter{


    SpecialOrderContract.View view;

    public SpecialOrderPresenter(SpecialOrderContract.View view) {
        this.view = view;
    }

    @Override
    public void displaySpecialAllItem() {
        BackendModel.Companion.getInstance().displaySpecialAllItem(new ModelCallback.GetAllItemCallback() {

            @Override
            public void getDataFailed(@NotNull String message) {

            }

            @Override
            public void getDataSucceed(@NotNull List<AvailableItemVO> itemList) {
                view.showTodayNormalOrder(itemList);

            }
        });
    }

    @Override
    public void addSpecialOrder(final String itemName, final String itemId, final String itemPrice, final String remark, final String itemAmount) {
        BackendModel.Companion.getInstance().getUser(new ModelCallback.LoginUserCallback() {

            @Override
            public void loginFailed(@NotNull String message) {

            }

            @Override
            public void loginSucceed(@NotNull UserVO userVO) {

                OrderItemVO orderItemVO = new OrderItemVO();
                orderItemVO.setItemName(itemName);
                orderItemVO.setItemId(itemId);
                orderItemVO.setItemPrice(itemPrice);
                orderItemVO.setItemCount(itemAmount);
                orderItemVO.setCustomerRemark(remark);
                orderItemVO.setCustomerName(userVO.getUserName());
                orderItemVO.setCustomerDepartment(userVO.getUserDepartment());
                orderItemVO.setCustomerId(userVO.getUserId());
                BackendModel.Companion.getInstance().addTodaySpecialOrder(Utils.Companion.getTodayDateNode(), orderItemVO, new ModelCallback.AddOrderCallback() {
                    @Override
                    public void addOrderSucceed(@NotNull String message) {
                        view.showSuccessfulOrder();
                    }

                    @Override
                    public void addOrderFailed(@NotNull String message) {

                    }
                });
            }
        });

    }
}
