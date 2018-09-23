package com.aceplus.hackthon.today_menu;

import com.aceplus.shared.Util.Utils;
import com.aceplus.shared.VO.AvailableItemVO;
import com.aceplus.shared.VO.UserVO;
import com.aceplus.shared.model.BackendModel;
import com.aceplus.shared.modelcallback.ModelCallback;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class TodayMenuPresenter implements TodayMenuContract.Presenter {

    private final TodayMenuContract.View view;

    public TodayMenuPresenter(TodayMenuContract.View view) {
        this.view = view;
    }

    @Override
    public void getTodayAvailableItem() {
        view.setLoading(true);
        BackendModel.Companion.getInstance().displayTodayAvailableItem(Utils.Companion.getTodayDateNode(), new ModelCallback.GetTodayAvailableItemCallback() {
            @Override
            public void getDataSucceed(@NotNull List<AvailableItemVO> itemList) {
                view.setLoading(false);
                view.displayTodayAvailableItem(itemList);
            }

            @Override
            public void getDataFailed(@NotNull String message) {
                view.setLoading(false);
            }
        });
    }

    @Override
    public void getUser() {
        view.setLoading(true);
        BackendModel.Companion.getInstance().getUser(new ModelCallback.LoginUserCallback() {

            @Override
            public void loginFailed(@NotNull String message) {
                view.setLoading(false);
            }

            @Override
            public void loginSucceed(@NotNull UserVO userVO) {
                view.setLoading(false);
                view.showUser(userVO);
            }

        });
    }

}
