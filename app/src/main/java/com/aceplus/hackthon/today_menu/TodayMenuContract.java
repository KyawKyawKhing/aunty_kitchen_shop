package com.aceplus.hackthon.today_menu;

import com.aceplus.shared.VO.AvailableItemVO;
import com.aceplus.shared.VO.UserVO;

import java.util.List;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class TodayMenuContract {


    interface View {

        void displayTodayAvailableItem(List<AvailableItemVO> itemList);

        void showUser(UserVO userVO);
    }

    interface Presenter {

        void getTodayAvailableItem();

        void getUser();
    }
}
