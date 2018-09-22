package com.aceplus.hackthon.login;

import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void addUser(UserVO userVO) {

        BackendModel.Companion.getInstance().addUser(userVO, new ModelCallback.LoginUserCallback() {
            @Override
            public void loginFailed(@NotNull String message) {

            }

            @Override
            public void loginSucceed(@NotNull UserVO userVO) {

            }
        });
    }
}

