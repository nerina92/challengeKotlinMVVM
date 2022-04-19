package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.network.ApiDataService
import com.example.challengemaxisistemaskotlin.room.BreedDao
import org.koin.dsl.module
import retrofit2.Retrofit

var DaoModule =module{
    single{ provideBreedDao(BreedDao::class.java)}

}

fun provideBreedDao(breedDao: Class<BreedDao>) {
    crea
}

fun provideApiDataService(retrofit: Retrofit, apiService: Class<ApiDataService>) =
    createDataService(retrofit, apiService)

fun <T> createDaoService(daoClass: Class<T>): T = retrofit.create(serviceClass)