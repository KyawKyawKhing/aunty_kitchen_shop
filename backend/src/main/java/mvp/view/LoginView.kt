package mvp.view

/**
 * Created by kkk on 9/22/2018.
 */
interface LoginView : BaseView {
    fun navigateToMainActivity()
    override fun displayMessage(message: String)
}