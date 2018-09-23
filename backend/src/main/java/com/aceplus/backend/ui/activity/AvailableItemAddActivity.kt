package com.aceplus.backend.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.aceplus.backend.R
import com.aceplus.backend.ui.adapter.ItemCountSpinnerAdapter
import com.aceplus.backend.ui.adapter.ItemNameSpinnerAdapter
import com.aceplus.shared.VO.AvailableItemVO
import kotlinx.android.synthetic.main.activity_available_item_add.*
import mvp.presenter.AvailableItemAddPresenter
import mvp.view.AvailableItemAddView

class AvailableItemAddActivity : AppCompatActivity(), AvailableItemAddView {
    private lateinit var mItemNameSpinnerAdapter: ItemNameSpinnerAdapter
    private lateinit var mItemCountSpinnerAdapter: ItemCountSpinnerAdapter
    private lateinit var mPresenter: AvailableItemAddPresenter
    private var itemPrice: Long = 0
    private var itemList = mutableListOf<AvailableItemVO>()
    private var itemCountList = mutableListOf<String>()

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, AvailableItemAddActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_item_add)
        backButton.setOnClickListener { finish() }
        mItemNameSpinnerAdapter = ItemNameSpinnerAdapter(applicationContext)
        mItemCountSpinnerAdapter = ItemCountSpinnerAdapter(applicationContext)
        spItemName.adapter = mItemNameSpinnerAdapter
        ivItemEdit.setOnClickListener { startActivity(NormalItemListActivity.newIntent(applicationContext)) }
        tvItemPrice.text = "800 Kyats"
        spItemCount.adapter = mItemCountSpinnerAdapter
        mPresenter = AvailableItemAddPresenter(this)
        mPresenter.displayItemNameSpinner()
        mPresenter.displayItemCountSpinner()
        spItemName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                itemPrice = itemList[position].itemPrice!!
                tvItemPrice.text = itemPrice.toString() + " Kyats"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        btnAddAvailableItem.setOnClickListener {
            if (spItemName.selectedItemPosition >= 0) {
                val itemVO = itemList[spItemName.selectedItemPosition]
                itemVO.itemCount = itemCountList[spItemCount.selectedItemPosition].toLong()
                mPresenter.addTodayAvailableItem(itemVO)
            } else
                Toast.makeText(applicationContext, "အသစ္ထည့္ရန္ ပစၥည္းမရွိပါ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun displayItemNameList(nameList: List<AvailableItemVO>) {
        if (nameList.isNotEmpty()) {
            itemPrice = nameList[0].itemPrice!!
            tvItemPrice.text = itemPrice.toString() + " Kyats"
            itemList = nameList as MutableList<AvailableItemVO>
            mItemNameSpinnerAdapter.newList = nameList
        }
    }

    override fun displayItemCountList(countList: List<String>) {
        itemCountList = countList as MutableList<String>
        mItemCountSpinnerAdapter.newList = countList
    }


    override fun displayMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
