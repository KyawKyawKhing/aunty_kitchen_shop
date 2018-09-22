package com.aceplus.backend.ui.viewholder

import android.view.View
import com.aceplus.backend.R
import com.aceplus.backend.delegate.NormalItemDelegate
import com.aceplus.shared.VO.AvailableItemVO
import kotlinx.android.synthetic.main.normal_item_listitem.view.*

/**
 * Created by kkk on 9/22/2018.
 */
class NormalItemViewHolder(itemView: View, val delegate: NormalItemDelegate) : BaseViewHolder<AvailableItemVO>(itemView) {
    override fun setData(data: AvailableItemVO) {
        itemView.itemName.setText(data.itemName)
        itemView.itemPrice.setText(data.itemPrice.toString())
        itemView.itemName.isFocusableInTouchMode = false
        itemView.itemPrice.isFocusableInTouchMode = false
        itemView.tag = 0
        itemView.btnItemEdit.setOnClickListener {
            if (itemView.tag == 0) {
                itemView.btnItemEdit.text = itemView.context.getText(R.string.save_item)
                itemView.tag = 1
                itemView.itemName.requestFocus()
                itemView.itemName.isFocusableInTouchMode = true
                itemView.itemPrice.isFocusableInTouchMode = true
            } else if (itemView.tag == 1) {
                itemView.tag = 0
                itemView.btnItemEdit.text = itemView.context.getText(R.string.edit_item)
                val itemVO = data
                itemVO.itemName = itemView.itemName.text.toString()
                itemVO.itemPrice = itemView.itemPrice.text.toString().toLong()
                delegate.onTapItemEdit(itemVO)
            }
        }
        itemView.btnItemDelete.setOnClickListener { delegate.onTapItemDelete(data.itemId!!) }
    }

    override fun onClick(v: View?) {
    }

}