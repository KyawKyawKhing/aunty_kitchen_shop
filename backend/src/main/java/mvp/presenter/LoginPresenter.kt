package mvp.presenter

import android.text.TextUtils
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.LoginView

/**
 * Created by kkk on 9/22/2018.
 */
class LoginPresenter(loginView: LoginView) : BasePresenter<LoginView>(loginView) {
    fun loginAdmin(username: String, password: String) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mView.displayMessage("Check UserName Or Password")
        } else {
            BackendModel.getInstance().loginAdmin(username, password, object : ModelCallback.LoginAdminCallback {
                override fun loginFailed(message: String) {
                    mView.displayMessage("Cannot Login")
                }

                override fun loginSucceed(userId: String) {
                    mView.navigateToMainActivity()
                }

            })
        }
    }
}