package mvp.view

import com.aceplus.shared.VO.OrderItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface ReportOrderListView : BaseView {
    fun displayReportOrderItemList(itemList: List<OrderItemVO>)
}