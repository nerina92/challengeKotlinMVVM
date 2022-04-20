package com.example.challengemaxisistemaskotlin.data.network

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.core.RetrofitHelper
import com.example.challengemaxisistemaskotlin.model.BreedImageData
import com.example.challengemaxisistemaskotlin.model.ListBreedsData
import com.example.challengemaxisistemaskotlin.room.SubBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ApiService (private val apiDataService: ApiDataService){
    /*private val retrofit = RetrofitHelper.getRetrofit()
    val apiDataService: ApiDataService = retrofit.create(
        ApiDataService::class.java
    )*/

    /*suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }*/

     suspend fun getAllBreeds(): ArrayList<String> {
         //este withContext(Dispatchers.IO)
        return withContext(Dispatchers.IO){
            val response = apiDataService.getAllBreeds()
            (response.body()?.message ?: ArrayList<String>()) as ArrayList<String>
        }
        /*val call: Call<ListBreedsData?>? = apiDataService.getAllBreeds()
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
         return api_response*/

    }

     suspend fun getBreedPhoto(breed:String) : String {
         return withContext(Dispatchers.IO){
             val response = apiDataService.getBreedPhoto(breed)
             (response.body()?.url ?: "")
         }
    }

     suspend fun getSubbreeds(breed: String): ArrayList<String> {
         return withContext(Dispatchers.IO){
             val response = apiDataService.getSubBreeds(breed)
             (response.body()?.message ?: ArrayList())
         }

    }

     suspend fun getSubbreedPhoto(breed: String?, subBreed: String): String {
         return withContext(Dispatchers.IO){
             val response = apiDataService.getSubBreedPhoto(breed,subBreed)
             (response.body()?.url ?: "")
         }

    }

}



