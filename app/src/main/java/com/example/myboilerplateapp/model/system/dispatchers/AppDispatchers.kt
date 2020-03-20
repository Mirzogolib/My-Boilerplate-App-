package com.example.myboilerplateapp.model.system.dispatchers

import kotlinx.coroutines.Dispatchers

class AppDispatchers : DispatcherProvider {
    override fun default() = Dispatchers.Default
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
}