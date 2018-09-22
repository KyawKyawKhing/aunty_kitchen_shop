package mvp.presenter

import com.aceplus.backend.Util.Utils
import com.aceplus.shared.VO.OrderItemVO
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.SpecialOrderListView

/**
 * Created by kkk on 9/22/2018.
 */
class SpecialOrderListPresenter(view: SpecialOrderListView) : BasePresenter<SpecialOrderListView>(view) {

    fun displayTodayOrderList() {
        BackendModel.getInstance().displayTodaySpecialOrder(Utils.getSpecialOrderDateNode(), object : ModelCallback.GetOrderCallback {
            override fun getDataSucceed(orderList: List<OrderItemVO>) {
                mView.displayTodayOrderItemList(orderList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage(message)
            }

        })
    }

    fun displayTomorrowOrderList() {
        BackendModel.getInstance().displayTodaySpecialOrder(Utils.getTodayDateNode(), object : ModelCallback.GetOrderCallback {
            override fun getDataSucceed(orderList: List<OrderItemVO>) {
                mView.displayTomorrowOrderItemList(orderList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage(message)
            }

        })
    }
}