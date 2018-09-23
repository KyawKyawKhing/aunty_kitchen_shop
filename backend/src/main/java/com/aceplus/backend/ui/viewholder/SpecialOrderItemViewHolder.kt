package com.aceplus.backend.ui.viewholder

import android.view.View
import com.aceplus.shared.VO.OrderItemVO
import kotlinx.android.synthetic.main.special_order_listitem.view.*

/**
 * Created by kkk on 9/22/2018.
 */
class SpecialOrderItemViewHolder(itemView: View) : BaseViewHolder<OrderItemVO>(itemView) {
    override fun setData(data: OrderItemVO) {
        itemView.itemName.text = data.itemName
        itemView.itemPrice.text = data.itemPrice
        itemView.itemCount.text = data.itemCount + " ပြဲ"
        itemView.customerName.text = data.customerName
        itemView.customerDept.text = data.customerDepartment
        itemView.customerRemark.text = data.customerRemark

        if (data.customerRemark == null || data.customerRemark!!.isEmpty()) {
            itemView.remark.visibility = View.GONE
            itemView.underview.visibility = View.GONE
        } else {
            itemView.remark.visibility = View.VISIBLE
            itemView.underview.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
    }

}