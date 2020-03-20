package com.example.myboilerplateapp.model.repository

import com.example.myboilerplateapp.model.data.Api
import com.example.myboilerplateapp.model.system.dispatchers.DispatcherProvider
import com.example.tractorent.entity.TokenApi
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.myboilerplateapp.entity.Result

class AuthRepository @Inject constructor(
    private val api: Api,
    private val dispatchers: DispatcherProvider
) {
    suspend fun getToken(login: String, email: String, password: String): Result<TokenApi> =
        withContext(dispatchers.io()) {
            try {
                val tokenApi = api.getTokenAsync(login, email, password).await()
                Result.Success(tokenApi)
//                MyResult.Success(tokenApi)
            } catch (ex: Exception) {
//                MyResult.Error<TokenApi>(api.getTokenAsync(login, password).)
                Result.Error<TokenApi>(ex)
            }
        }
}
