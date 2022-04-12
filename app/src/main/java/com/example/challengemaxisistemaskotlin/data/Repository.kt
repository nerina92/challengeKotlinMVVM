package com.example.challengemaxisistemaskotlin.data

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.data.network.ApiService

class Repository constructor(){
    private val api = ApiService()

     fun getAllBreeds (): MutableLiveData<List<String>?>? {
        return api.getAllBreeds()
    }

    suspend fun getAllBreeds2(){
        api.getAllBreeds2()
    }

    fun getBreedPhoto(breeds: List<String?>): MutableLiveData<List<String>>?{
        return api.getBreedPhoto(breeds)
    }

    suspend fun getSubbreedPhoto (breed: String?, breeds: List<String?>){
        api.getSubbreedPhoto(breed,breeds)
    }
    suspend fun getSubbreeds (breed: String?){
        api.getSubbreeds(breed)
    }
}