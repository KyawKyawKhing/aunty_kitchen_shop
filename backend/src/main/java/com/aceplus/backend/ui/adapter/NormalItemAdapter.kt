package com.aceplus.backend.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.aceplus.backend.R
import com.aceplus.backend.delegate.NormalItemDelegate
import com.aceplus.backend.ui.viewholder.NormalItemViewHolder
import com.aceplus.shared.VO.AvailableItemVO

/**
 * Created by kkk on 9/22/2018.
 */
class NormalItemAdapter(context: Context, private val delegate: NormalItemDelegate) : BaseRecyclerAdapter<AvailableItemVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalItemViewHolder {
        val view = mLayoutInflater!!.inflate(R.layout.normal_item_listitem, parent, false)
        return NormalItemViewHolder(view, delegate)
    }

}