package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.network.ApiService
import org.koin.dsl.module


val apiServiceModule = module {
    factory {
        ApiService(get())
    }

}
