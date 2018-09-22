package com.aceplus.backend.delegate

import com.aceplus.shared.VO.OrderItemVO

/**
 * Created by kkk on 9/22/2018.
 */
interface OrderItemDelegate {
    fun onTapSentOrder(orderItemVO: OrderItemVO)
}
