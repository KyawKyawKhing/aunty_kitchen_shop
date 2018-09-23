package com.aceplus.backend.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.aceplus.backend.R
import kotlinx.android.synthetic.main.activity_login.*
import mvp.presenter.LoginPresenter
import mvp.view.LoginView

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var preferences: SharedPreferences
    private lateinit var mPresenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        if (preferences.getString("isLogin", "false") == "true") {
            navigateToMainActivity()
        }

        mPresenter = LoginPresenter(this)
        btnLogin.setOnClickListener { mPresenter.loginAdmin(edtUsername.text.toString(), edtPassword.text.toString()) }
    }

    override fun navigateToMainActivity() {
        preferences.edit().putString("isLogin", "true").apply()
        val intent = MainActivity.newIntent(applicationContext)
        startActivity(intent)
        finish()
    }

    override fun displayMessage(message: String) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
