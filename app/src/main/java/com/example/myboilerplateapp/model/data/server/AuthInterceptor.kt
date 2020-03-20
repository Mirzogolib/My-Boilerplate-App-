package com.example.myboilerplateapp.model.data.server

import com.example.myboilerplateapp.model.system.AuthHolder
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authHolder: AuthHolder
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = authHolder.token
        if (!token.isNullOrBlank())
            request.newBuilder()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Authorization", token)
                .build()
        return chain.proceed(request)
    }
}