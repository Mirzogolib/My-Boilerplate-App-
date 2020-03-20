package com.example.myboilerplateapp.entity

sealed class Result<out T> {
    data class Success<T>(val payload: T) : Result<T>()
    data class Error<T>(val exception: Exception) : Result<T>()
}