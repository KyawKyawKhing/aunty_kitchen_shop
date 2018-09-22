package com.aceplus.backend.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.aceplus.backend.R
import com.aceplus.backend.delegate.OrderItemDelegate
import com.aceplus.backend.ui.viewholder.OrderItemViewHolder
import com.aceplus.shared.VO.OrderItemVO

/**
 * Created by kkk on 9/22/2018.
 */
class TodayOrderItemAdapter(context: Context, val delegate: OrderItemDelegate) : BaseRecyclerAdapter<OrderItemVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = mLayoutInflater!!.inflate(R.layout.order_listitem, parent, false)
        return OrderItemViewHolder(view, delegate)
    }

}