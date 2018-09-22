package com.aceplus.hackthon.user_profile;

import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
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
