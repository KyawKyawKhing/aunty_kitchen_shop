package com.aceplus.hackthon;

import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {


    MainActivityContract.View view;


    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void getUser() {
        BackendModel.Companion.getInstance().getUser(new ModelCallback.LoginUserCallback() {
            @Override
            public void loginSucceed(@NotNull UserVO userVO) {
                view.showUser(userVO);
            }

            @Override
            public void loginFailed(@NotNull String message) {

            }
        });
    }
}
