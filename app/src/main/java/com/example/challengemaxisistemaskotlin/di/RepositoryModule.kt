package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.Repository
import com.example.challengemaxisistemaskotlin.data.network.ApiService
import com.example.challengemaxisistemaskotlin.room.BreedsRoomDatabase
import org.koin.dsl.module

val repositoryModule = module {
    single{
        Repository(get(),get(), get(),get(),get())
    }

}

