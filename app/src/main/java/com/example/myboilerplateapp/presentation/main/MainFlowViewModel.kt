package com.example.myboilerplateapp.presentation.main

import com.example.myboilerplateapp.model.interactor.MainInteractor
import com.example.myboilerplateapp.presentation.base.BaseViewModel
import com.example.myboilerplateapp.router.FlowRouter
import com.example.myboilerplateapp.router.HomeFlow
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainFlowViewModel @Inject constructor(
    private val router: Router,
    private val flowRouter: FlowRouter,
    private val mainInteractor: MainInteractor
) : BaseViewModel() {
    override fun onFirstViewAttach() {
        flowRouter.newRootScreen(HomeFlow)
        viewModelScope.launch {

        }
        super.onFirstViewAttach()
    }

    fun onFavClicked() {
//        router.newRootScreen(FavouriteFlow)
    }
}