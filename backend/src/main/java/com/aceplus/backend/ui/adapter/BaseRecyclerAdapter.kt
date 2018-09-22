package com.aceplus.backend.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.aceplus.backend.ui.viewholder.BaseViewHolder
import java.util.*

/**
 * Created by kkk on 9/22/2018.
 */

abstract class BaseRecyclerAdapter<O>(context: Context) : RecyclerView.Adapter<BaseViewHolder<O>>() {
    private var mData: MutableList<O>? = null
    protected var mLayoutInflater: LayoutInflater? = null

    init {
        mData = ArrayList()
        mLayoutInflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<O>, position: Int) {
        holder.setData(mData!![position])
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    fun setNewList(newList: MutableList<O>) {
        mData = newList
        notifyDataSetChanged()
    }

    fun getList(): MutableList<O> {
        return mData!!
    }

    val items: List<O>
        get() {
            val data = mData
            return data ?: ArrayList()
        }

    fun getItemAt(position: Int): O? {
        return if (position < mData!!.size - 1) mData!![position] else null

    }

}