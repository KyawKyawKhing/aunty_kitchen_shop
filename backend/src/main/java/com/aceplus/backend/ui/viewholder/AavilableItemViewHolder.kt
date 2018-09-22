package com.aceplus.backend.ui.viewholder

import android.view.View
import com.aceplus.backend.delegate.AvailableItemDelegate
import com.aceplus.shared.VO.AvailableItemVO
import kotlinx.android.synthetic.main.available_item_listitem.view.*

/**
 * Created by kkk on 9/22/2018.
 */
class AavilableItemViewHolder(itemView: View, val delegate: AvailableItemDelegate) : BaseViewHolder<AvailableItemVO>(itemView) {
    override fun setData(data: AvailableItemVO) {
        if (data.itemCount != null && data.itemCount!!.toString() != "0") {
            itemView.itemName.text = data.itemName
            itemView.itemPrice.text = data.itemPrice.toString() + " kyats"
            itemView.itemRemaining.text = data.itemCount.toString() + " remaining"
        } else {
            itemView.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
    }

}