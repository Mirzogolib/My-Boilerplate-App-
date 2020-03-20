package com.example.myboilerplateapp.model.interactor

import android.util.Log
import com.example.myboilerplateapp.model.repository.AuthRepository
import com.example.myboilerplateapp.model.system.AuthHolder
import javax.inject.Inject
import com.example.myboilerplateapp.entity.Result
import com.example.tractorent.entity.TokenApi

class AuthInteractor @Inject constructor(
    private val repository: AuthRepository,
    private val authHolder: AuthHolder
) {

    suspend fun signIn(login: String, email: String, password: String): Result<TokenApi> {
        val result = repository.getToken(login, email, password)
        if (result is Result.Success) {
            authHolder.token = result.payload.data.token
            authHolder.id = result.payload.data.id
        } else if (result is Result.Error)
            Log.d("RESULT", result.toString())
        return result
    }
}