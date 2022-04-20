package com.example.challengemaxisistemaskotlin.ui.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengemaxisistemaskotlin.data.Repository
import com.example.challengemaxisistemaskotlin.ui.view.MainActivity
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class BreedsViewModel constructor(private val repository : Repository):ViewModel(){
    var breeds= MutableLiveData<ArrayList<String>?>()
    var breedsPhoto=MutableLiveData<ArrayList<String>>()
    var subbreeds = MutableLiveData<ArrayList<String>?>()
    var subBreedsPhoto=MutableLiveData<ArrayList<String>>()

    /*Los métodos siguientes llaman al repositorio desde el ModelView*/
     fun getBreeds() {
        //repository.getAllBreeds2();
        viewModelScope.launch {
            breeds.postValue(repository.getAllBreeds())
        }
    }

    fun getBreedPhoto(breeds: ArrayList<String>?) {
        viewModelScope.launch {
            breedsPhoto.postValue(breeds?.let { repository.getBreedPhoto(it) })
        }
    }

     fun getSubbreeds(breed: String) {
        viewModelScope.launch {
            subbreeds.postValue(repository.getSubbreeds(breed))
        }
    }

     fun getSubbreedPhoto(
         breed: String,
         subbreeds: ArrayList<String>?
    ) {
        viewModelScope.launch {
             subBreedsPhoto.postValue(subbreeds?.let { repository.getSubbreedPhoto(breed, it) })
        }
    }


}