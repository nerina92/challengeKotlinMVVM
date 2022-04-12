package com.example.challengemaxisistemaskotlin.ui.modelView

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challengemaxisistemaskotlin.data.Repository

class BreedsViewModelFactory constructor(private val repository: Repository):ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BreedsViewModel::class.java)) {
            BreedsViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
