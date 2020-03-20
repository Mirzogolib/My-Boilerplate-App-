package com.example.myboilerplateapp.presentation.main

import com.example.myboilerplateapp.model.interactor.MainInteractor
import com.example.myboilerplateapp.presentation.base.BaseViewModel
import com.example.myboilerplateapp.router.AuthFlow
import com.example.myboilerplateapp.router.MainFlow
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainViewModel  @Inject constructor(
    private val interactor: MainInteractor,
    private val router: Router
) : BaseViewModel() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (interactor.isLoggedIn()) {
            router.newRootScreen(MainFlow)
        } else {
            router.newRootScreen(AuthFlow)
        }

    }
}