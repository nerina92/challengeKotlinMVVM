package com.example.challengemaxisistemaskotlin.data

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.data.network.ApiService
import com.example.challengemaxisistemaskotlin.room.*

class Repository constructor(private val api:ApiService,
                             private val daoBreeds:BreedDao,
                             private val daoImage:ImageDao,
                             private val daoSubBreed: SubBreed){
    //private val api = ApiService()

     suspend fun getAllBreeds (): MutableLiveData<ArrayList<String>?> {
         val lista:MutableLiveData<ArrayList<String>?>
         lista=api.getAllBreeds()
         if(lista.value!!.size>0){
             insertBreedsRoom(lista.value!!)

         }else{
             lista.postValue(getBreedsRoom())
         }
         return lista
    }

    suspend fun getBreedPhoto(breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>?{
        val lista:MutableLiveData<ArrayList<String>>?
        lista=api.getBreedPhoto(breeds)
        if (lista != null) {
            if(lista.value!!.size>0) {
                insertPhotos(lista.value!!,breeds)

            }else{
                lista.postValue(getBreedsPhotoRoom())
            }
        }
        return lista
    }

    private suspend fun insertPhotos(photos: ArrayList<String>, breeds: ArrayList<String>) {
        for(i in photos.indices){
            var img=Image(photos[i])
            daoImage.insert(img)
            var list=daoImage.obtenerMaxId()
            println("list obtenida de obtenerMaxId():")
            for(j in list.indices){
                println("${list[j].idImage} - ${list[j].url}")
            }
            daoBreeds.update(list[0].idImage,breeds[i])
        }

    }

    fun getSubbreedPhoto (breed: String?, breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>? {
        return api.getSubbreedPhoto(breed,breeds)
    }
     fun getSubbreeds (breed: String): MutableLiveData<ArrayList<String>?>? {
        return api.getSubbreeds(breed)
    }

    fun getBreedsRoom():ArrayList<String>{
        var listBreeds =daoBreeds.getBreeds()
        var listNombres = ArrayList<String>()
        for (i in listBreeds.indices){
            listNombres.add(listBreeds.get(i).breedName)
        }
        return listNombres
    }
    suspend fun insertBreedsRoom(value: ArrayList<String>) {
        var breed:Breed
        for (i in value.indices){
            breed=Breed(value[i])
            daoBreeds.insert(breed)
        }

    }
    fun getBreedsPhotoRoom(): ArrayList<String> {
        var listBreeds =daoBreeds.getBreeds()
        var listPhothos = ArrayList<String>()
        for(i in listBreeds.indices){
            listPhothos.add(daoImage.getImage(listBreeds[i].idImage).url)
        }
        return listPhothos
    }

}
