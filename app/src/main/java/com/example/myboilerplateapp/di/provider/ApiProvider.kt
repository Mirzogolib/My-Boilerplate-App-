package com.example.myboilerplateapp.di.provider

import com.example.myboilerplateapp.di.ServerPath
import com.example.myboilerplateapp.model.data.Api
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(private val okHttpClient: OkHttpClient,
                                      @ServerPath private val serverPath: String) : Provider<Api> {
    override fun get(): Api =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .client(okHttpClient)
            .baseUrl(serverPath)
            .build()
            .create(Api::class.java)
}