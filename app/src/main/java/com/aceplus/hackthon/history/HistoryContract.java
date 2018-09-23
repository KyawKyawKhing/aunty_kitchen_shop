package com.aceplus.hackthon.history;

import com.aceplus.shared.VO.OrderItemVO;

import java.util.List;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class HistoryContract {

    interface View {

        void showOrderList(List<OrderItemVO> orderList);

        void showOrderListByDate(List<OrderItemVO> orderList);

        void showLoading(boolean active);
    }

    interface presenter {

        void displayAllOrderByUser(String userId);

        void displayOrderByDateByUser(String date, String UserId);
    }
}