package com.example.myboilerplateapp

import android.app.Application
import com.example.myboilerplateapp.di.DI
import com.example.myboilerplateapp.di.module.AppModule
import com.example.myboilerplateapp.di.module.ServerModule
import com.jakewharton.threetenabp.AndroidThreeTen
import toothpick.Toothpick
import java.util.*

class App  : Application() {

    override fun onCreate() {
        super.onCreate()
        appCode = UUID.randomUUID().toString()
        initToothpick()
        initTime()
    }


    private fun initTime() {
        AndroidThreeTen.init(this)
    }

    private fun initToothpick() {
        Toothpick.openScope(DI.APP_SCOPE).apply {
            installModules(AppModule(applicationContext), ServerModule())
        }
    }

    companion object {
        lateinit var appCode: String
            private set
    }

}