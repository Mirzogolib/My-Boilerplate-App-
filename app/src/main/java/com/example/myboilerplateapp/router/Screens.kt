package com.example.myboilerplateapp.router

import androidx.fragment.app.Fragment
import com.example.myboilerplateapp.ui.auth.AuthFragment
import com.example.myboilerplateapp.ui.home.HomeFragment
import com.example.myboilerplateapp.ui.main.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AuthFlow : SupportAppScreen() {
    override fun getFragment(): Fragment = AuthFragment()
}

object MainFlow : SupportAppScreen() {
    override fun getFragment(): Fragment = MainFlowFragment()
}

object HomeFlow : SupportAppScreen() {
    //HomeFragment
    override fun getFragment(): Fragment = HomeFragment()
}