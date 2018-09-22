package com.aceplus.backend.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.aceplus.backend.R
import com.aceplus.backend.delegate.AvailableItemDelegate
import com.aceplus.backend.ui.viewholder.AavilableItemViewHolder
import com.aceplus.shared.VO.AvailableItemVO

/**
 * Created by kkk on 9/22/2018.
 */
class AvailableItemAdapter(context: Context, private val availableItemDelegate: AvailableItemDelegate) : BaseRecyclerAdapter<AvailableItemVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AavilableItemViewHolder {
        val view = mLayoutInflater!!.inflate(R.layout.available_item_listitem, parent, false)
        return AavilableItemViewHolder(view, availableItemDelegate)
    }

}