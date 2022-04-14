package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.network.ApiDataService
import org.koin.dsl.module
import retrofit2.Retrofit

var apiDataServiceModule=module{
    /*factory {
        ApiDataService ()
    }*/
   single{ provideApiDataService(get(),ApiDataService::class.java)}

}
fun provideApiDataService(retrofit: Retrofit, apiService: Class<ApiDataService>) =
    createDataService(retrofit, apiService)

fun <T> createDataService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)
