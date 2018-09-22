package com.aceplus.backend.ui.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.aceplus.backend.R
import com.aceplus.backend.ui.adapter.SpecialOrderItemAdapter
import com.aceplus.shared.VO.OrderItemVO
import kotlinx.android.synthetic.main.activity_report_list.*
import mvp.presenter.ReportOrderListPresenter
import mvp.view.ReportOrderListView
import java.util.*

class ReportListActivity : AppCompatActivity(), ReportOrderListView {

    private lateinit var mOrderAdapter: SpecialOrderItemAdapter
    private lateinit var mPresenter: ReportOrderListPresenter

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ReportListActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_list)
        backButton.setOnClickListener { finish() }
        mPresenter = ReportOrderListPresenter(this)
        mOrderAdapter = SpecialOrderItemAdapter(applicationContext)
        rvReportList.adapter = mOrderAdapter
        rvReportList.layoutManager = GridLayoutManager(applicationContext, 1)
        filterDate.setOnClickListener { showDateAlert() }
        mPresenter.displayAllReportOrderList()
    }

    private fun showDateAlert() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in textbox
            //            lblDate.setText("" + dayOfMonth + " " + MONTHS[monthOfYear] + ", " + year)
            var fMonth = (monthOfYear + 1).toString()
            if (fMonth.length < 2) {
                fMonth = "0" + fMonth
            }
            filterDate.text = dayOfMonth.toString() + "-" + fMonth + "-" + year.toString()
            mPresenter.displayReportOrderListByDate(filterDate.text.toString())
        }, year, month, day)
        dpd.show()
        dpd.datePicker.maxDate = System.currentTimeMillis()
    }


    override fun displayMessage(message: String) {

    }

    override fun displayReportOrderItemList(itemList: List<OrderItemVO>) {
        if (itemList.isEmpty())
            emptyItemView.visibility = View.VISIBLE
        else
            emptyItemView.visibility = View.GONE

        mOrderAdapter.setNewList(itemList as MutableList<OrderItemVO>)
    }

}
