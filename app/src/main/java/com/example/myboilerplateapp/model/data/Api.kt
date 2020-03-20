package com.example.myboilerplateapp.model.data

import com.example.tractorent.entity.TokenApi
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    companion object {
        private const val API_VERSION = "/api/"
    }

    @FormUrlEncoded
//    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("${API_VERSION}login")
    fun getTokenAsync(
        @Field("phone") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Deferred<TokenApi>
}