package com.aceplus.hackthon.history;

import com.aceplus.shared.VO.OrderItemVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class HistoryPresenter implements HistoryContract.presenter {

    HistoryContract.View view;

    public HistoryPresenter(HistoryContract.View view) {
        this.view = view;
    }

    @Override
    public void displayAllOrderByUser(String userId) {
        view.showLoading(true);
        BackendModel.Companion.getInstance().displayAllOrderByUser(userId, new ModelCallback.GetOrderCallback() {
            @Override
            public void getDataSucceed(@NotNull List<OrderItemVO> orderList) {
                view.showLoading(false);
                view.showOrderList(orderList);
            }

            @Override
            public void getDataFailed(@NotNull String message) {
                view.showLoading(false);

            }
        });
    }

    @Override
    public void displayOrderByDateByUser(String date,String userId) {
        view.showLoading(true);
        BackendModel.Companion.getInstance().displayOrderByDateByUser(date,userId, new ModelCallback.GetOrderCallback() {
            @Override
            public void getDataSucceed(@NotNull List<OrderItemVO> orderList) {
                view.showLoading(false);
                view.showOrderListByDate(orderList);
            }

            @Override
            public void getDataFailed(@NotNull String message) {
                view.showLoading(false);

            }
        });
    }
}
