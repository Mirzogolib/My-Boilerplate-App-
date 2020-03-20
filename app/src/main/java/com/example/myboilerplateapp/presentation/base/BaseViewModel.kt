package com.example.myboilerplateapp.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private var isFirstViewAttach = true
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun init() {
        if (isFirstViewAttach) {
            onFirstViewAttach()
            isFirstViewAttach = false
        }
    }

    open fun onFirstViewAttach() {}
}
