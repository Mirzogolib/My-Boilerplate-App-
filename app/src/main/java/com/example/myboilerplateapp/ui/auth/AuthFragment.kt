package com.example.myboilerplateapp.ui.auth

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myboilerplateapp.R
import com.example.myboilerplateapp.di.DI
import com.example.myboilerplateapp.di.factory.ViewModelFactory
import com.example.myboilerplateapp.extensions.toast
import com.example.myboilerplateapp.extensions.visible
import com.example.myboilerplateapp.presentation.auth.AuthViewModel
import com.example.myboilerplateapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth.*
import toothpick.Toothpick

class AuthFragment  : BaseFragment() {
    override var layoutRes: Int = R.layout.fragment_auth
    private val viewModelFactory: ViewModelFactory by lazy { ViewModelFactory(DI.APP_SCOPE) }
    private val viewModel: AuthViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(AuthViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Toothpick.openScopes(DI.MAIN_SCOPE)

        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        init()
    }

    private fun init() {
        viewModel.progress.observe(this, Observer {
            loginB.visible(!it)
            progress.visible(it)
        })
        viewModel.toast.observe(this, Observer {
            toast(it)
        })
        loginB.setOnClickListener {
            when {
                emailOrPhoneET.text.toString().trim().contains("@") -> viewModel.signIn(
                    "",
                    emailOrPhoneET.text.toString(),
                    password.text.toString()
                )
                emailOrPhoneET.text.toString().trim().contains("+") -> viewModel.signIn(
                    emailOrPhoneET.text.toString(),
                    "",
                    password.text.toString()
                )
                else -> toast("Enter in correct form")
            }

        }

        registerB.setOnClickListener {
            viewModel.registerClicked()
        }

        resetPasswordTV.setOnClickListener {
            viewModel.resetClicked()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRealRemoving())
            Toothpick.closeScope(DI.AUTH_SCOPE)
    }

    override fun onBackPressed() {
        viewModel.onBackPressed()
        super.onBackPressed()
    }

}