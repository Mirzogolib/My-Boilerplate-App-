package com.example.myboilerplateapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.myboilerplateapp.R
import com.example.myboilerplateapp.di.DI
import com.example.myboilerplateapp.di.factory.ViewModelFactory
import com.example.myboilerplateapp.di.module.FlowNavigationModule
import com.example.myboilerplateapp.extensions.setImage
import com.example.myboilerplateapp.extensions.setLaunchScreen
import com.example.myboilerplateapp.presentation.main.MainFlowViewModel
import com.example.myboilerplateapp.router.HomeFlow
import com.example.myboilerplateapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_flow.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import toothpick.Toothpick
import javax.inject.Inject

class MainFlowFragment : BaseFragment(), View.OnClickListener {


    override var layoutRes: Int = R.layout.fragment_main_flow
    @Inject
    lateinit var router: Router
    private val viewModelFactory: ViewModelFactory by lazy { ViewModelFactory(DI.MAIN_SCOPE) }
    private val currentFragment get() = childFragmentManager.findFragmentById(R.id.container) as BaseFragment?


    private val viewModel: MainFlowViewModel by lazy {
        ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainFlowViewModel::class.java)
    }

    @Inject
    internal lateinit var navigatorHolder: NavigatorHolder

    //    private val navigator: Navigator by lazy {
//        object : SupportAppNavigator(activity!!, childFragmentManager, R.id.container) {}
//    }
    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this.activity, childFragmentManager, R.id.container) {
            override fun activityBack() = router.exit()

            override fun setupFragmentTransaction(
                command: Command?,
                currentF: Fragment?,
                nextF: Fragment?,
                ft: FragmentTransaction
            ) {
                ft.setReorderingAllowed(true)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        prepareScope(isFirstLaunch(savedInstanceState))
        super.onCreate(savedInstanceState)


        if (childFragmentManager.fragments.isEmpty()) {
            navigator.setLaunchScreen(HomeFlow)
        }

    }

    override fun onClick(p0: View?) {
        if (p0 != null) {

            when (p0.id) {
                R.id.homeButton -> {
                    Log.d("CLICK", "Home Clicked")
                    navigator.setLaunchScreen(HomeFlow)
                    setImage(
                        R.id.homeButton,
                        homeButton,
                        favouriteButton,
                        publishButton,
                        accountButton
                    )
                }
                R.id.favouriteButton -> {
                    Log.d("CLICK", "Favourite Clicked")
//                    navigator.setLaunchScreen(FavouriteFlow)
                    setImage(
                        R.id.favouriteButton,
                        homeButton,
                        favouriteButton,
                        publishButton,
                        accountButton
                    )
                }
                R.id.publishButton -> {
                    Log.d("CLICK", "Publish Clicked")
//                    navigator.setLaunchScreen(PublishFlow)
                    setImage(
                        R.id.publishButton,
                        homeButton,
                        favouriteButton,
                        publishButton,
                        accountButton
                    )
                }
                R.id.accountButton -> {
                    Log.d("CLICK", "Account Clicked")
//                    navigator.setLaunchScreen(AccountFlow)
                    setImage(
                        R.id.accountButton,
                        homeButton,
                        favouriteButton,
                        publishButton,
                        accountButton
                    )
                }

            }


        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.init()


        homeButton.setOnClickListener(this)
        favouriteButton.setOnClickListener(this)
        publishButton.setOnClickListener(this)
        accountButton.setOnClickListener(this)

    }

    private fun prepareScope(firstTime: Boolean) {
        val scope = Toothpick.openScopes(DI.MAIN_SCOPE)
        if (firstTime) {
            scope.installModules(
                FlowNavigationModule(scope.getInstance(Router::class.java))
            )
        }
        Toothpick.inject(this@MainFlowFragment, scope)
    }


    override fun onResume() {
        navigatorHolder.setNavigator(navigator)
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
//        val adapter = menuRecycler.adapter as? MainMenuAdapter
//        val position = adapter?.selectedItemPosition ?: 0
//        outState.putInt(SELECTED_MENU_POSITION, position)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_MENU_POSITION)) {
//            val adapter = menuRecycler.adapter as? MainMenuAdapter
//            val position = savedInstanceState.getInt(SELECTED_MENU_POSITION, 0)
//            adapter?.selectedItemPosition = position
//            adapter?.notifyDataSetChanged()
//        }
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}