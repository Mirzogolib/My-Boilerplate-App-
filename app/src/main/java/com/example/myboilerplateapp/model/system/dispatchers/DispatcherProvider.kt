package com.example.myboilerplateapp.model.system.dispatchers

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    fun io(): CoroutineContext
    fun ui(): CoroutineContext
    fun default(): CoroutineContext
}