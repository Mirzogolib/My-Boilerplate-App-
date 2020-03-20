package com.example.myboilerplateapp.model.interactor

import com.example.myboilerplateapp.model.system.AuthHolder
import javax.inject.Inject

class MainInteractor  @Inject constructor(
    private val authHolder: AuthHolder
) {

    fun isLoggedIn() = !authHolder.token.isNullOrBlank()

    fun logOut() {
        authHolder.clear()
    }
}