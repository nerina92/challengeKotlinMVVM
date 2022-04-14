package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.network.ApiDataService
import com.example.challengemaxisistemaskotlin.data.network.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class NetworkModule {
    val networkModule = module {
        single { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        //single { provideApiService(get(), ApiService::class.java) }
        single { provideApiService(get(), get()) }
    }

    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://dog.ceo")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
fun provideApiService(retrofit: Retrofit, apiService: Class<ApiDataService>) =
    createService(retrofit, apiService)

fun <T> createService(retrofit: Retrofit, serviceClass: Class<T>): T = retrofit.create(serviceClass)
