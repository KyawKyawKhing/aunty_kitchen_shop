package com.aceplus.backend.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by kkk on 9/22/2018.
 */
abstract class BaseViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mData: W? = null

    init {
        itemView.setOnClickListener(this)
    }

    abstract fun setData(data: W)
}