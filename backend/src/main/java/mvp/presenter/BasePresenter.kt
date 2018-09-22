package mvp.presenter

import mvp.view.BaseView

/**
 * Created by kkk on 9/22/2018.
 */
abstract class BasePresenter<T : BaseView> constructor(val view: T) {

    protected lateinit var mView: T

    init {
        this.mView = view
    }
}