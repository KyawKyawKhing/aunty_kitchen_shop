package com.aceplus.backend.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.aceplus.backend.R
import com.aceplus.backend.ui.viewholder.SpecialOrderItemViewHolder
import com.aceplus.shared.VO.OrderItemVO

/**
 * Created by kkk on 9/22/2018.
 */
class SpecialOrderItemAdapter(context: Context) : BaseRecyclerAdapter<OrderItemVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialOrderItemViewHolder {
        val view = mLayoutInflater!!.inflate(R.layout.special_order_listitem, parent, false)
        return SpecialOrderItemViewHolder(view)
    }

}