package com.example.myboilerplateapp.di.provider

import com.example.myboilerplateapp.BuildConfig
import com.example.myboilerplateapp.model.data.server.AuthInterceptor
import com.example.myboilerplateapp.model.system.AuthHolder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Provider

class OkHttpClientProvider @Inject constructor(private val authHolder: AuthHolder) :
    Provider<OkHttpClient> {
    override fun get(): OkHttpClient {
        return with(OkHttpClient.Builder()) {
            readTimeout(30, TimeUnit.SECONDS)
            addNetworkInterceptor(AuthInterceptor(authHolder))
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }.run { addNetworkInterceptor(this) }
            }
            build()
        }
    }
}