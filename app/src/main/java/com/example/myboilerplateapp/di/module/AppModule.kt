package com.example.myboilerplateapp.di.module

import android.content.Context
import com.example.myboilerplateapp.BuildConfig
import com.example.myboilerplateapp.di.ServerPath
import com.example.myboilerplateapp.model.system.AuthHolder
import com.example.myboilerplateapp.model.system.AuthPrefs
import com.example.myboilerplateapp.model.system.ResourceManager
import com.example.myboilerplateapp.model.system.dispatchers.AppDispatchers
import com.example.myboilerplateapp.model.system.dispatchers.DispatcherProvider
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module

class AppModule (context: Context) : Module()  {
    init {
        bind(String::class.java).withName(ServerPath::class.java).toInstance(BuildConfig.ORIGIN_ENDPOINT)
        bind(Context::class.java).toInstance(context)
        bind(AuthHolder::class.java).to(AuthPrefs::class.java).singletonInScope()
        bind(ResourceManager::class.java).singletonInScope()
        bind(DispatcherProvider::class.java).toInstance(AppDispatchers())
        val cicerone = Cicerone.create()
        bind(Router::class.java).toInstance(cicerone.router)
        bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
    }
}