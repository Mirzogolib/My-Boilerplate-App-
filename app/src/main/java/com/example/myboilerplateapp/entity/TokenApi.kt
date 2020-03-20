package com.example.tractorent.entity

import com.google.gson.annotations.SerializedName

data class TokenApi (
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : Token
)