package com.example.challengemaxisistemaskotlin.di

import com.example.challengemaxisistemaskotlin.ui.modelView.BreedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BreedsViewModel(get()) }
}