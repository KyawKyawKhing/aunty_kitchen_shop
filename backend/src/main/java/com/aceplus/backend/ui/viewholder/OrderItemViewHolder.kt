package com.aceplus.backend.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.aceplus.backend.delegate.OrderItemDelegate
import com.aceplus.shared.VO.OrderItemVO
import kotlinx.android.synthetic.main.order_listitem.view.*

/**
 * Created by kkk on 9/22/2018.
 */
class OrderItemViewHolder(itemView: View, val delegate: OrderItemDelegate) : BaseViewHolder<OrderItemVO>(itemView) {
    override fun setData(data: OrderItemVO) {
        if (data.isSentOrder == "1") {
            itemView.visibility = View.GONE
            itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 1)
        } else {
            itemView.visibility = View.VISIBLE
            itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 240)
            val params = itemView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(16, 16, 16, 16)
            itemView.layoutParams = params

            itemView.itemName.text = data.itemName
            itemView.itemPrice.text = data.itemPrice
            itemView.itemCount.text = data.itemCount + " ပြဲ"
            itemView.customerName.text = data.customerName
            itemView.customerDept.text = data.customerDepartment
            itemView.customerRemark.text = data.customerRemark
            if (data.customerRemark != null && data.customerRemark!!.isEmpty()) {
                itemView.remark.visibility = View.GONE
            } else {
                itemView.remark.visibility = View.VISIBLE
            }
            itemView.btnSentOrder.setOnClickListener {
                data.isSentOrder = "1"
                delegate.onTapSentOrder(data)
            }
        }
    }

    override fun onClick(v: View?) {
    }

}