package com.example.myboilerplateapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.myboilerplateapp.R
import com.example.myboilerplateapp.di.DI
import com.example.myboilerplateapp.di.factory.ViewModelFactory
import com.example.myboilerplateapp.presentation.home.HomeViewModel
import com.example.myboilerplateapp.ui.base.BaseFragment
import toothpick.Toothpick

class HomeFragment : BaseFragment() {
    override var layoutRes: Int = R.layout.fragment_home
    private val viewModelFactory: ViewModelFactory by lazy { ViewModelFactory(DI.APP_SCOPE) }
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScopes(DI.APP_SCOPE))
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
    }

}