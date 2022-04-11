package com.example.challengemaxisistemaskotlin.model

import com.google.gson.annotations.SerializedName

data class ListBreedsData(
    @SerializedName("message")var message: ArrayList<String?>? = null,
    @SerializedName("status")var status: String
)
