package com.example.challengemaxisistemaskotlin.data.network

import com.example.challengemaxisistemaskotlin.model.BreedImageData
import com.example.challengemaxisistemaskotlin.model.ListBreedsData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDataService {
    @GET("/api/breeds/list")
     fun getAllBreeds(): Call<ListBreedsData?>?

    @GET("/api/breed/{breed}/images/random")
     fun getBreedPhoto(@Path("breed") breed: String?): Call<BreedImageData?>?

    @GET("/api/breed/{breed}/list")
    fun getSubBreeds(@Path("breed") breed: String?): Call<ListBreedsData?>?

    @GET("/api/breed/{breed}/{sub_breed}/images/random")
    fun getSubBreedPhoto(
        @Path("breed") breed: String?,
        @Path("sub_breed") sub_breed: String?
    ): Call<BreedImageData?>?
}