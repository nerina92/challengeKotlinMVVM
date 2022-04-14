package com.example.challengemaxisistemaskotlin.data

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.data.network.ApiService

class Repository constructor(private val api:ApiService){
    //private val api = ApiService()

     fun getAllBreeds (): MutableLiveData<ArrayList<String>?> {
        return api.getAllBreeds()
    }

    fun getBreedPhoto(breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>?{
        return api.getBreedPhoto(breeds)
    }

     fun getSubbreedPhoto (breed: String?, breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>? {
        return api.getSubbreedPhoto(breed,breeds)
    }
     fun getSubbreeds (breed: String): MutableLiveData<ArrayList<String>?>? {
        return api.getSubbreeds(breed)
    }
}