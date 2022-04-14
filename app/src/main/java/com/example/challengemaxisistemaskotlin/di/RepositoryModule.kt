package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.Repository
import com.example.challengemaxisistemaskotlin.data.network.ApiService
import org.koin.dsl.module

val repositoryModule = module {
    /*factory {
        Repository()
    }*/

   /* fun provideApiServiceRepository(apiService: ApiService) : ApiService {
        return ApiService()
    }

    single { provideApiServiceRepository(get()) }*/
    factory{
        Repository(get())
    }
}

