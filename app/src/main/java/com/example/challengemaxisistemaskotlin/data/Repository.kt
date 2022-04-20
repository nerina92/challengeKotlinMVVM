package com.example.challengemaxisistemaskotlin.data

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.data.network.ApiService
import com.example.challengemaxisistemaskotlin.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class Repository constructor(private val api:ApiService,
                             private val daoBreeds:BreedDao,
                             private val daoImage:ImageDao,
                             private val daoSubBreed: SubBreedDao){
    //private val api = ApiService()

     suspend fun getAllBreeds (): ArrayList<String> {
         var retorno: java.util.ArrayList<String>
         val response = api.getAllBreeds()
         if(response.isEmpty()){
             //traer de la base de datos
             retorno=getBreedsRoom()
         }else{
             retorno=response
             //lo mando al IO thread
             withContext(Dispatchers.IO){
                 insertBreedsRoom(retorno)
             }
         }
         return retorno
    }

    suspend fun getBreedPhoto(breeds: ArrayList<String>): ArrayList<String>{
        var retorno = arrayListOf<String>()
        for(i in breeds.indices){
            val response = api.getBreedPhoto(breeds[i])
            if(response != "")
                retorno.add(response)
        }
        if(retorno.isEmpty()){
            //traer de la base de datos
            retorno=getBreedsPhotoRoom(breeds,true)
        }else{
            //lo mando al IO thread
            withContext(Dispatchers.IO){
                insertPhotos(retorno,breeds, true)
            }
        }
        return retorno
    }
    suspend fun getSubbreeds (breed: String): ArrayList<String> {
        var retorno: ArrayList<String>
        var response=api.getSubbreeds(breed)
        if(response.isEmpty()){
            //traer de la base de datos
            retorno=getSubBreedsRoom(breed)
        }else{
            retorno=response
            //lo mando al IO thread
            withContext(Dispatchers.IO){
                insertSubBreedsRoom(retorno, breed)
            }
        }
        return retorno
    }


    suspend fun getSubbreedPhoto (breed: String, breeds: ArrayList<String>): ArrayList<String> {
        var retorno = arrayListOf<String>()
        for(i in breeds.indices){
            val response = api.getSubbreedPhoto(breed,breeds[i])
            if(response != "")
                retorno.add(response)
        }
        if(retorno.isEmpty()){
            //traer de la base de datos
            retorno=getBreedsPhotoRoom(breeds, false)
        }else{
            //lo mando al IO thread
            withContext(Dispatchers.IO){
                insertPhotos(retorno,breeds, false)
            }
        }
        return retorno
    }


   suspend fun getBreedsRoom():ArrayList<String>{
       var listNombres = ArrayList<String>()
       withContext(Dispatchers.IO) {
           for (i in daoBreeds.getBreeds().indices) {
               listNombres.add(daoBreeds.getBreeds()[i].breedName)
           }
       }
        return listNombres
    }
    suspend fun insertBreedsRoom(value: ArrayList<String>) {
        var breed:Breed
        for (i in value.indices){
            breed=Breed()
            breed.breedName=value[i]
            daoBreeds.insert(breed)
        }

    }
   suspend fun getBreedsPhotoRoom(breeds:ArrayList<String>, isBreed: Boolean): ArrayList<String> {
        var listPhothos = ArrayList<String>()
       var id:Int
       withContext(Dispatchers.IO) {
           for(i in breeds.indices){
               if(isBreed) {
                   id = daoBreeds.searchImage(breeds[i])
               }else{
                   id=daoSubBreed.searchImage(breeds[i])
               }
               listPhothos.add(daoImage.getImage(id).url)
           }
       }
        return listPhothos
    }
    private suspend fun insertPhotos(photos: ArrayList<String>, breeds: ArrayList<String> , isBreed : Boolean) {
        for(i in photos.indices){
            var img=Image()
            img.url=photos[i]
            var id=daoImage.insert(img)//daoImage.obtenerMaxId()
            println("list obtenida de obtenerMaxId():$id")
            if(isBreed){
                daoBreeds.update(id,breeds[i])
            }else{
                daoSubBreed.update(id, breeds[i])
            }
        }
    }
    suspend fun insertSubBreedsRoom(value: ArrayList<String>, breed:String) {
        var subBreed : SubBreed
        val id = daoBreeds.searchId(breed.lowercase(Locale.getDefault()))
        for (i in value.indices) {
            subBreed = SubBreed()
            subBreed.subBredName = value[i]
            subBreed.idBreed = id
            daoSubBreed.insert(subBreed)
        }
    }

    suspend fun getSubBreedsRoom(breed:String):ArrayList<String>{
        var listSubBreeds = ArrayList<String>()
        withContext(Dispatchers.IO){
            var id = daoBreeds.searchId(breed)
            for (i in daoSubBreed.searchSubBreeds(id).indices){
                listSubBreeds.add(daoSubBreed.getSubBreeds()[i].subBredName)
            }
        }
        return listSubBreeds
    }
}
