package com.aceplus.backend.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.aceplus.backend.R
import kotlinx.android.synthetic.main.activity_login.*
import mvp.presenter.LoginPresenter
import mvp.view.LoginView

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var mPresenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter = LoginPresenter(this)
        btnLogin.setOnClickListener { mPresenter.loginAdmin(edtUsername.text.toString(), edtPassword.text.toString()) }
    }

    override fun navigateToMainActivity() {
        val intent = MainActivity.newIntent(applicationContext)
        startActivity(intent)
    }

    override fun displayMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
