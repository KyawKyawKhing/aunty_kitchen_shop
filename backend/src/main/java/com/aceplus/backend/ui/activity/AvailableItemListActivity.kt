package com.aceplus.backend.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.aceplus.backend.R
import com.aceplus.backend.Util.Utils
import com.aceplus.backend.ui.adapter.AvailableItemAdapter
import com.aceplus.shared.VO.AvailableItemVO
import kotlinx.android.synthetic.main.activity_available_item_list.*
import mvp.presenter.AvailableItemListPresenter
import mvp.view.AvailableItemListView

class AvailableItemListActivity : AppCompatActivity(), AvailableItemListView {
    override fun displayMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private lateinit var mPresenter: AvailableItemListPresenter
    private lateinit var mAdapter: AvailableItemAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, AvailableItemListActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_item_list)
        mPresenter = AvailableItemListPresenter(this)
        backButton.setOnClickListener { finish() }
        fabAddItem.setOnClickListener { startActivity(AvailableItemAddActivity.newIntent(applicationContext)) }
        rvItemList.layoutManager = GridLayoutManager(applicationContext, 1)
        mAdapter = AvailableItemAdapter(applicationContext, mPresenter)
        rvItemList.adapter = mAdapter
        mPresenter.displayTodayAvailableItemList(Utils.getTodayDateNode())
    }

    override fun displayAvailableItemList(itemList: List<AvailableItemVO>) {
        if (itemList.isNotEmpty()) {
            emptyItemView.visibility = View.GONE
        }
        mAdapter.setNewList(itemList as MutableList<AvailableItemVO>)
    }

}
