package com.aceplus.backend.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.aceplus.backend.R
import com.aceplus.backend.ui.adapter.SpecialOrderItemAdapter
import com.aceplus.shared.VO.OrderItemVO
import kotlinx.android.synthetic.main.activity_special_order.*
import mvp.presenter.SpecialOrderListPresenter
import mvp.view.SpecialOrderListView

class SpecialOrderActivity : AppCompatActivity(), SpecialOrderListView {
    private lateinit var mTodayAdapter: SpecialOrderItemAdapter
    private lateinit var mTomorrowAdapter: SpecialOrderItemAdapter
    private lateinit var mPresenter: SpecialOrderListPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SpecialOrderActivity::class.java)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_order)
        backButton.setOnClickListener { finish() }
        mPresenter = SpecialOrderListPresenter(this)
        mTodayAdapter = SpecialOrderItemAdapter(applicationContext)
        mTomorrowAdapter = SpecialOrderItemAdapter(applicationContext)

        rvTodayOrderItem.adapter = mTodayAdapter
        rvTodayOrderItem.layoutManager = GridLayoutManager(applicationContext, 1)
        rvTomorrowOrderItem.adapter = mTomorrowAdapter
        rvTomorrowOrderItem.layoutManager = GridLayoutManager(applicationContext, 1)

        mPresenter.displayTodayOrderList()
        mPresenter.displayTomorrowOrderList()

        btnTodayOrder.setOnClickListener {
            btnTodayOrder.setBackgroundResource(R.drawable.bg_tab_left_selected)
            btnTomorrowOrder.setBackgroundResource(R.drawable.bg_tab_right_default)
            rvTodayOrderItem.visibility = View.VISIBLE
            rvTomorrowOrderItem.visibility = View.GONE
            if (mTodayAdapter.getList().isEmpty())
                emptyBuyView.visibility = View.VISIBLE
            else
                emptyOrderView.visibility = View.GONE
        }
        btnTomorrowOrder.setOnClickListener {
            btnTodayOrder.setBackgroundResource(R.drawable.bg_tab_left_default)
            btnTomorrowOrder.setBackgroundResource(R.drawable.bg_tab_right_selected)
            rvTodayOrderItem.visibility = View.GONE
            rvTomorrowOrderItem.visibility = View.VISIBLE
            if (mTomorrowAdapter.getList().isEmpty())
                emptyOrderView.visibility = View.VISIBLE
            else
                emptyBuyView.visibility = View.GONE
        }
    }

    override fun displayMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun displayTodayOrderItemList(itemList: List<OrderItemVO>) {
        if (itemList.isEmpty())
            emptyBuyView.visibility = View.VISIBLE
        else
            emptyBuyView.visibility = View.GONE
        mTodayAdapter.setNewList(itemList as MutableList<OrderItemVO>)
    }

    override fun displayTomorrowOrderItemList(itemList: List<OrderItemVO>) {
        mTomorrowAdapter.setNewList(itemList as MutableList<OrderItemVO>)
    }

}
