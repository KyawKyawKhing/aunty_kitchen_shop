package com.aceplus.hackthon;

import com.aceplus.shared.VO.UserVO;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class MainActivityContract {

    interface View {

        void showUser(UserVO userVO);
    }

    interface Presenter {

        void getUser();
    }
}
