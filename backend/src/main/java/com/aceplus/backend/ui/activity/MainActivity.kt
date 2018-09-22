package com.aceplus.backend.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aceplus.backend.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSell.setOnClickListener { startActivity(AvailableItemListActivity.newIntent(applicationContext)) }
        btnTodayNormalOrder.setOnClickListener { }
        btnSpecialOrder.setOnClickListener { }
    }
}
