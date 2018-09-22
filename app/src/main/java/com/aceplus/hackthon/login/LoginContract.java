package com.aceplus.hackthon.login;

import com.aceplus.shared.VO.UserVO;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class LoginContract {

    interface View {
        void loginSuccess();
    }

    interface Presenter{

        void addUser(UserVO userVO);

    }
}
