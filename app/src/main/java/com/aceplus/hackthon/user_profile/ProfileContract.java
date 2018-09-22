package com.aceplus.hackthon.user_profile;

import com.aceplus.shared.VO.UserVO;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class ProfileContract {

    interface View {
        void showUser(UserVO userVO);
    }

    interface Presenter {

        void getUser();
    }
}
