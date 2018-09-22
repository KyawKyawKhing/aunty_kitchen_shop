package com.aceplus.backend.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.aceplus.backend.R
import com.aceplus.backend.Util.Utils
import com.aceplus.backend.ui.adapter.NormalItemAdapter
import com.aceplus.shared.VO.AvailableItemVO
import kotlinx.android.synthetic.main.activity_normal_item_list.*
import kotlinx.android.synthetic.main.add_new_item.*
import mvp.presenter.NormalItemListPresenter
import mvp.view.NormalItemListView

class NormalItemListActivity : AppCompatActivity(), NormalItemListView {

    private lateinit var mAdapter: NormalItemAdapter
    private lateinit var mPresenter: NormalItemListPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NormalItemListActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_item_list)
        backButton.setOnClickListener { finish() }
        mPresenter = NormalItemListPresenter(this)
        mAdapter = NormalItemAdapter(applicationContext, mPresenter)
        rvNormalItem.adapter = mAdapter
        rvNormalItem.layoutManager = GridLayoutManager(applicationContext, 1)
        mPresenter.displayNormalItemList()
        addNormalItem.setOnClickListener { showAddItemAlert() }
    }

    private fun showAddItemAlert() {
        val alert = AlertDialog.Builder(this)
        alert.setView(R.layout.add_new_item)
        val alertDialog = alert.create()
        alertDialog.show()
        alertDialog.btnItemAdd.setOnClickListener {
            if (alertDialog.itemName.text.toString().isNotEmpty() || alertDialog.itemPrice.text.toString().isNotEmpty()) {
                val itemVO = AvailableItemVO()
                itemVO.itemId = Utils.getRadomId()
                itemVO.itemName = alertDialog.itemName.text.toString()
                itemVO.itemPrice = alertDialog.itemPrice.text.toString().toLong()
                mPresenter.addNormalItem(itemVO)
                alertDialog.dismiss()
            } else if (alertDialog.itemName.text.isEmpty()) {
                alertDialog.itemName.error = "အမည္ထည့္ပါ"
            } else if (alertDialog.itemPrice.text.isEmpty()) {
                alertDialog.itemPrice.error = ""
            }
        }
    }

    override fun displayMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun displayNormalItemList(itemList: List<AvailableItemVO>) {
        if (itemList.isEmpty())
            emptyItemView.visibility = View.VISIBLE
        else
            emptyItemView.visibility = View.GONE

        mAdapter.setNewList(itemList as MutableList<AvailableItemVO>)
    }

}
