package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.network.ApiService
import org.koin.dsl.module


val apiServiceModule = module {
    //Factory crea una instancia diferente cada vez que es llamado (a diferencia de single)
    factory {
        ApiService(get())
    }

}
