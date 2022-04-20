package com.example.challengemaxisistemaskotlin.data.network

import com.example.challengemaxisistemaskotlin.model.BreedImageData
import com.example.challengemaxisistemaskotlin.model.ListBreedsData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDataService{
    @GET("/api/breeds/list")
    suspend fun getAllBreeds(): Response<ListBreedsData>

    @GET("/api/breed/{breed}/images/random")
    suspend fun getBreedPhoto(@Path("breed") breed: String?): Response<BreedImageData>

    @GET("/api/breed/{breed}/list")
    suspend fun getSubBreeds(@Path("breed") breed: String?): Response<ListBreedsData>

    @GET("/api/breed/{breed}/{sub_breed}/images/random")
     suspend fun getSubBreedPhoto(
        @Path("breed") breed: String?,
        @Path("sub_breed") sub_breed: String?
    ): Response<BreedImageData>
}