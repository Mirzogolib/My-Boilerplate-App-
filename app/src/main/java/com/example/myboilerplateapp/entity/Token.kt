package com.example.tractorent.entity

import com.google.gson.annotations.SerializedName

//data class Token(val token: String)


data class Token(
    @SerializedName("token") val token: String,
    @SerializedName("id") val id: Int
)