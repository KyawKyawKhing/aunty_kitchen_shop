package com.aceplus.backend.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.aceplus.backend.R
import com.aceplus.backend.ui.adapter.TodayOrderItemAdapter
import com.aceplus.shared.VO.OrderItemVO
import kotlinx.android.synthetic.main.activity_today_order_list.*
import mvp.presenter.OrderListPresenter
import mvp.view.OrderListView

class TodayOrderListActivity : AppCompatActivity(), OrderListView {
    override fun getView(): View {
        return getView()
    }

    private lateinit var mAdapter: TodayOrderItemAdapter
    private lateinit var mPresenter: OrderListPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, TodayOrderListActivity::class.java)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_order_list)
        backButton.setOnClickListener { finish() }
        mPresenter = OrderListPresenter(this)
        mAdapter = TodayOrderItemAdapter(applicationContext, mPresenter)
        rvOrderItem.adapter = mAdapter
        rvOrderItem.layoutManager = GridLayoutManager(applicationContext, 1)

        mPresenter.displayOrderList()
    }

    override fun displayOrderItemList(itemList: List<OrderItemVO>) {
        if (itemList.isEmpty())
            emptyItemView.visibility = View.VISIBLE
        else {
            emptyItemView.visibility = View.GONE
            if (applicationContext != null)
                mAdapter.setNewList(itemList as MutableList<OrderItemVO>)
        }
    }

    override fun displayMessage(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
