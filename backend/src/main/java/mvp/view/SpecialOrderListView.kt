package mvp.view

import com.aceplus.shared.VO.OrderItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface SpecialOrderListView : BaseView {
    fun displayTodayOrderItemList(itemList: List<OrderItemVO>)
    fun displayTomorrowOrderItemList(itemList: List<OrderItemVO>)
}