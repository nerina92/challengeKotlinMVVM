package com.example.challengemaxisistemaskotlin.ui.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengemaxisistemaskotlin.data.Repository
import kotlinx.coroutines.launch

class BreedsViewModel constructor(private val repository : Repository):ViewModel(){
    var breeds= MutableLiveData<ArrayList<String>?>()
    var breedsPhoto=MutableLiveData<ArrayList<String>>()
    var subbreeds = MutableLiveData<ArrayList<String>?>()
    var subBreedsPhoto=MutableLiveData<ArrayList<String>>()

    /*Los métodos siguientes llaman al repositorio desde el ModelView*/
     fun getBreeds() {
        //repository.getAllBreeds2();
        viewModelScope.launch {
            breeds = repository.getAllBreeds()
        }
    }

    fun getBreedPhoto(breeds: ArrayList<String>?) {
        viewModelScope.launch {
            breedsPhoto= breeds?.let { repository.getBreedPhoto(it) }!!
        }
    }

     fun getSubbreeds(breed: String) {
        viewModelScope.launch {
            subbreeds= repository.getSubbreeds(breed)!!
        }
    }

     fun getSubbreedPhoto(
         breed: String,
         subbreeds: ArrayList<String>?
    ) {
        viewModelScope.launch {
             subBreedsPhoto=subbreeds?.let { repository.getSubbreedPhoto(breed, it) }!!
        }
    }
}