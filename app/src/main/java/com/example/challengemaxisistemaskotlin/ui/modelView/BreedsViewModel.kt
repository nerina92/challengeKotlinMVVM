package com.example.challengemaxisistemaskotlin.ui.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengemaxisistemaskotlin.data.Repository

class BreedsViewModel constructor(private val repository : Repository):ViewModel(){
    var breeds= MutableLiveData<List<String>?>()
    var breedsPhoto=MutableLiveData<List<String>>()

    /*Los métodos siguientes llaman al repositorio desde el ModelView*/
     fun getBreeds() {
        //repository.getAllBreeds2();
       breeds= repository.getAllBreeds()!!
    }

    fun getBreedPhoto(breeds: List<String?>?) {
        breedsPhoto= breeds?.let { repository.getBreedPhoto(it) }!!
    }

    suspend fun getSubbreeds(breed: String?) {
        return repository.getSubbreeds(breed)
    }

    suspend fun getSubbreedPhoto(
        breed: String?,
        subbreeds: List<String?>?
    ) {
        return subbreeds?.let { repository.getSubbreedPhoto(breed, it) }!!
    }
}