package com.example.myboilerplateapp.di.module

import com.example.myboilerplateapp.di.provider.ApiProvider
import com.example.myboilerplateapp.di.provider.OkHttpClientProvider
import com.example.myboilerplateapp.model.data.Api
import com.example.myboilerplateapp.model.interactor.AuthInteractor
import com.example.myboilerplateapp.model.repository.AuthRepository
import okhttp3.OkHttpClient
import toothpick.config.Module

class ServerModule : Module() {

    init {
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java)
        bind(Api::class.java).toProvider(ApiProvider::class.java).providesSingletonInScope()
        bind(AuthRepository::class.java)
        bind(AuthInteractor::class.java)
    }
}