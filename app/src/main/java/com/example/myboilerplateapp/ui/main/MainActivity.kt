package com.example.myboilerplateapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.myboilerplateapp.R
import com.example.myboilerplateapp.di.DI
import com.example.myboilerplateapp.di.factory.ViewModelFactory
import com.example.myboilerplateapp.presentation.main.MainViewModel
import com.example.myboilerplateapp.ui.base.BaseFragment
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val currentFragment
        get() = supportFragmentManager.findFragmentById(R.id.mainContainer) as BaseFragment?

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders
            .of(this, ViewModelFactory(DI.MAIN_SCOPE))
            .get(MainViewModel::class.java)
    }
    private val navigator: SupportAppNavigator by lazy {
        SupportAppNavigator(this, R.id.mainContainer)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScopes(DI.APP_SCOPE, DI.MAIN_SCOPE))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.init()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing)
            Toothpick.closeScope(DI.MAIN_SCOPE)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}