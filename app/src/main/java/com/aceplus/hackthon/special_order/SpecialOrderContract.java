package com.aceplus.hackthon.special_order;

import com.aceplus.shared.VO.AvailableItemVO;

import java.util.List;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class SpecialOrderContract {

    interface View {

        void showTodayNormalOrder(List<AvailableItemVO> orderList);

        void showSuccessfulOrder();

    }

    interface Presenter {

        void displaySpecialAllItem();

        void addSpecialOrder(String itemName, String itemId, String itemPrice, String remark, String itemAmount);

    }
}
