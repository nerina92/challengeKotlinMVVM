package com.example.challengemaxisistemaskotlin.model

import com.google.gson.annotations.SerializedName

data class BreedImageData(
    @SerializedName("message") var url: String? = null,
    @SerializedName("status") var status: String? = null
)
