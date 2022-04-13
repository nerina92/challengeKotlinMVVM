package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.data.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        Repository(get())
    }
}