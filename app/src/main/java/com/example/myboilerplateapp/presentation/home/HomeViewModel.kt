package com.example.myboilerplateapp.presentation.home

import com.example.myboilerplateapp.presentation.base.BaseViewModel
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val router: Router
): BaseViewModel() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

//    fun finish(){
//        router.newRootChain(AuthFlow)
//    }
}