package com.example.challengemaxisistemaskotlin.data.network

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.core.RetrofitHelper
import com.example.challengemaxisistemaskotlin.model.BreedImageData
import com.example.challengemaxisistemaskotlin.model.ListBreedsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


     fun getAllBreeds2() {

        val call: Call<ListBreedsData?>? = apiDataService.getAllBreeds()
        call!!.enqueue(object : Callback<ListBreedsData?> {
            override fun onResponse(
                call: Call<ListBreedsData?>,
                response: Response<ListBreedsData?>
            ) {
                if (response.isSuccessful()) {
                    val modal: ListBreedsData? = response.body()
                    System.out.println(modal.toString())
                }
            }

            override fun onFailure(call: Call<ListBreedsData?>, t: Throwable) {
                // displaying an error message in toast
                println("ERROR CALL RETROFIT" + t.fillInStackTrace())
            }
        })
    }

     fun getAllBreeds(): MutableLiveData<ArrayList<String>?> {

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

     fun getBreedPhoto(breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>? {

        val api_response = MutableLiveData<ArrayList<String>>()
        val urls = ArrayList<String>()
        for (i in breeds.indices) {
            val call: Call<BreedImageData?>? = apiDataService.getBreedPhoto(breeds[i])
            //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
            call!!.enqueue(object : Callback<BreedImageData?> {
                override fun onResponse(
                    call: Call<BreedImageData?>,
                    response: Response<BreedImageData?>
                ) {
                    //Llamada exitosa
                    val imageData: BreedImageData? = response.body()
                    if (imageData != null) {
                        imageData.url?.let { urls.add(it) }
                    }
                    api_response.setValue(urls)
                    //System.out.println("Size urls="+urls.size());
                }

                override fun onFailure(call: Call<BreedImageData?>, t: Throwable) {
                    //Resultado erroneo
                    println("ERROR: " + t.message)
                    urls.add("")
                }
            })
        }
        return api_response
    }

     fun getSubbreeds(breed: String?): MutableLiveData<ArrayList<String>?>? {

        val call: Call<ListBreedsData?>? = apiDataService.getSubBreeds(breed)
        val api_response = MutableLiveData<ArrayList<String>?>()
        //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
        call!!.enqueue(object : Callback<ListBreedsData?> {
            override fun onResponse(
                call: Call<ListBreedsData?>,
                response: Response<ListBreedsData?>
            ) {
                //Llamada exitosa
                val respuesta: ListBreedsData? = response.body()
                println("RESPUESTA SUBRAZAS: " + respuesta.toString())
                if (respuesta != null) {
                    api_response.setValue(respuesta.message)
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

     fun getSubbreedPhoto(breed: String?, breeds: ArrayList<String>): MutableLiveData<ArrayList<String>>? {

        val api_response = MutableLiveData<ArrayList<String>>()
        val urls = ArrayList<String>()
        for (i in breeds.indices) {
            val call: Call<BreedImageData?>? = apiDataService.getSubBreedPhoto(
                breed,
                breeds[i]
            )
            //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
            call!!.enqueue(object : Callback<BreedImageData?> {
                override fun onResponse(
                    call: Call<BreedImageData?>,
                    response: Response<BreedImageData?>
                ) {
                    //Llamada exitosa
                    val imageData: BreedImageData? = response.body()
                    if (imageData != null) {
                        imageData.url?.let { urls.add(it) }
                    }
                    println("FOTO DESDE LA API: $imageData.toString()")
                    api_response.setValue(urls)
                }

                override fun onFailure(call: Call<BreedImageData?>, t: Throwable) {
                    //Resultado erroneo
                    println("ERROR: " + t.message)
                    urls.add("")
                }
            })
        }
        return api_response
    }

}



