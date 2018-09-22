package mvp.presenter

import com.aceplus.backend.Util.Utils
import com.aceplus.backend.delegate.OrderItemDelegate
import com.aceplus.shared.VO.OrderItemVO
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.OrderListView

/**
 * Created by kkk on 9/22/2018.
 */
class OrderListPresenter(view: OrderListView) : BasePresenter<OrderListView>(view), OrderItemDelegate {
    fun displayOrderList() {
        BackendModel.getInstance().displayTodayNormalOrder(Utils.getTodayDateNode(), object : ModelCallback.GetOrderCallback {
            override fun getDataSucceed(orderList: List<OrderItemVO>) {
                mView.displayOrderItemList(orderList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage(message)
            }

        })
    }

    override fun onTapSentOrder(orderItemVO: OrderItemVO) {
        BackendModel.getInstance().changeSentOrder(Utils.getTodayDateNode(), orderItemVO)
    }

}