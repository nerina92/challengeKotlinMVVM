package com.example.challengemaxisistemaskotlin.data

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.data.network.ApiDataService
import com.example.challengemaxisistemaskotlin.data.network.ApiService
import com.example.challengemaxisistemaskotlin.model.ListBreedsData
import com.example.challengemaxisistemaskotlin.room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository constructor(private val api:ApiService,
                             private val daoBreeds:BreedDao,
                             private val daoImage:ImageDao,
                             private val daoSubBreed: SubBreedDao,
                             private val apiDataService: ApiDataService){


     suspend fun getAllBreeds (): MutableLiveData<ArrayList<String>?> {
         val call: Call<ListBreedsData?>? = apiDataService.getAllBreeds()
         val api_response = MutableLiveData<ArrayList<String>?>()
         //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
         call!!.enqueue(object : Callback<ListBreedsData?> {
             override fun onResponse(
                 call: Call<ListBreedsData?>,
                 response: Response<ListBreedsData?>
             ) {
                 //Llamada exitosa
                 val respuesta: ListBreedsData? = response.body()
                 println("RESPUESTA: " + respuesta.toString())

                 if (respuesta != null) {
                     api_response.setValue(respuesta.message)
                     insertBreedsRoom(respuesta.message)
                 }else{
                     var empty_list= ArrayList<String>()
                     api_response.setValue(empty_list)
                 }
             }

             override fun onFailure(call: Call<ListBreedsData?>, t: Throwable) {
                 //Resultado erroneo
                 println("ERROR CALL RETROFIT" + t.fillInStackTrace())
                 var empty_list= ArrayList<String>()
                 api_response.setValue(empty_list)
             }
         })
         return api_response
    }

    suspend fun getBreedPhoto(breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>?{
        val lista:MutableLiveData<ArrayList<String>>?
        lista=api.getBreedPhoto(breeds)
        if (lista != null) {
           // if(lista.value!!.size>0) {
             //   insertPhotos(lista.value!!,breeds)
            //}else{
               // lista.postValue(getBreedsPhotoRoom())
            //}
        }
        /*lista!!.observe(this){
            b-> if (breeds != null) {
                if (!breeds.isEmpty()) {
                    insertPhotos(b,breeds)
                }
            }
        }*/

        return lista
    }

    private suspend fun insertPhotos(photos: ArrayList<String>, breeds: ArrayList<String>) {
        for(i in photos.indices){
            var img=Image()
            img.url=photos[i]

            var id=daoImage.insert(img)//daoImage.obtenerMaxId()
            println("list obtenida de obtenerMaxId():$id")
            /*for(j in list.indices){
                println("${list[j].idImage} - ${list[j].url}")
            }*/
            daoBreeds.update(id,breeds[i])
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
     fun insertBreedsRoom(value: ArrayList<String>) {
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
