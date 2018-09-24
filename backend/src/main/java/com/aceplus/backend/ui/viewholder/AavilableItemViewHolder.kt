package com.aceplus.backend.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.aceplus.backend.delegate.AvailableItemDelegate
import com.aceplus.shared.VO.AvailableItemVO
import kotlinx.android.synthetic.main.available_item_listitem.view.*


/**
 * Created by kkk on 9/22/2018.
 */
class AavilableItemViewHolder(itemView: View, val delegate: AvailableItemDelegate) : BaseViewHolder<AvailableItemVO>(itemView) {
    val height = itemView.height
    override fun setData(data: AvailableItemVO) {
        if (data.itemCount != null && data.itemCount!!.toString() != "0") {
            itemView.itemName.text = data.itemName
            itemView.itemPrice.text = data.itemPrice.toString() + " က်ပ္"
            itemView.itemRemaining.text = data.itemCount.toString() + " remaining"
            itemView.visibility = View.VISIBLE
//            itemView.layoutParams = itemView.layoutParams
            itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 220)
            val params = itemView.layoutParams as  ViewGroup.MarginLayoutParams
            params.setMargins(16, 16, 16, 16)
            itemView.layoutParams = params
        } else {
            itemView.visibility = View.GONE
            itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }

    }

    override fun onClick(v: View?) {
    }

}