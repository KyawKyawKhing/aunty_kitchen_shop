package mvp.presenter

import com.aceplus.backend.Util.Utils
import com.aceplus.shared.VO.OrderItemVO
import com.aceplus.shared.model.BackendModel
import com.aceplus.shared.modelcallback.ModelCallback
import mvp.view.ReportOrderListView

/**
 * Created by kkk on 9/22/2018.
 */
class ReportOrderListPresenter(view: ReportOrderListView) : BasePresenter<ReportOrderListView>(view) {

    fun displayAllReportOrderList() {
        BackendModel.getInstance().displayAllOrderByAdmin(object : ModelCallback.GetOrderCallback {
            override fun getDataSucceed(orderList: List<OrderItemVO>) {
                mView.displayReportOrderItemList(orderList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage(message)
            }

        })
    }

    fun displayReportOrderListByDate(reportNode: String) {
        BackendModel.getInstance().displayOrderByDateByAdmin(reportNode, object : ModelCallback.GetOrderCallback {
            override fun getDataSucceed(orderList: List<OrderItemVO>) {
                mView.displayReportOrderItemList(orderList)
            }

            override fun getDataFailed(message: String) {
                mView.displayMessage(message)
            }

        })
    }
}