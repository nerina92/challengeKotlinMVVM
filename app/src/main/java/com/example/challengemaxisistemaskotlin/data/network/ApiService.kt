package com.example.challengemaxisistemaskotlin.data.network

import androidx.lifecycle.MutableLiveData
import com.example.challengemaxisistemaskotlin.core.RetrofitHelper
import com.example.challengemaxisistemaskotlin.model.BreedImageData
import com.example.challengemaxisistemaskotlin.model.ListBreedsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiService {
    private val retrofit = RetrofitHelper.getRetrofit()

    /*suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }*/


    suspend fun getAllBreeds2() {
        val apiDataService: ApiDataService = retrofit.create(
            ApiDataService::class.java
        )
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

     fun getAllBreeds(): MutableLiveData<List<String>?>? {
        val apiDataService: ApiDataService = retrofit.create(
            ApiDataService::class.java
        )
        val call: Call<ListBreedsData?>? = apiDataService.getAllBreeds()
        val api_response = MutableLiveData<List<String>?>()
        //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
        call!!.enqueue(object : Callback<ListBreedsData?> {
            override fun onResponse(
                call: Call<ListBreedsData?>,
                response: Response<ListBreedsData?>
            ) {
                //Llamada exitosa
                val respuesta: ListBreedsData? = response.body()
                System.out.println("RESPUESTA: " + respuesta.toString())
                //como hago el get al atributo message?
                if (respuesta != null) {
                    api_response.setValue(respuesta.message)
                }
            }

            override fun onFailure(call: Call<ListBreedsData?>, t: Throwable) {
                //Resultado erroneo
                println("ERROR CALL RETROFIT" + t.fillInStackTrace())
                api_response.setValue(null)
            }
        })
        return api_response
    }

     fun getBreedPhoto(breeds: List<String?>): MutableLiveData<List<String>>? {
        val apiDataService: ApiDataService = retrofit.create(
            ApiDataService::class.java
        )
        val api_response = MutableLiveData<List<String>>()
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

    suspend fun getSubbreeds(breed: String?): MutableLiveData<List<String>?>? {
        val apiDataService: ApiDataService = retrofit.create(
            ApiDataService::class.java
        )
        val call: Call<ListBreedsData?>? = apiDataService.getSubBreeds(breed)
        val api_response = MutableLiveData<List<String>?>()
        //call.enqueque hace que la llamada no se realice en el proceso o hilo principal, sino en uno secundario.
        call!!.enqueue(object : Callback<ListBreedsData?> {
            override fun onResponse(
                call: Call<ListBreedsData?>,
                response: Response<ListBreedsData?>
            ) {
                //Llamada exitosa
                val respuesta: ListBreedsData? = response.body()
                System.out.println("RESPUESTA: " + respuesta.toString())
                if (respuesta != null) {
                    api_response.setValue(respuesta.message)
                }
            }

            override fun onFailure(call: Call<ListBreedsData?>, t: Throwable) {
                //Resultado erroneo
                println("ERROR CALL RETROFIT" + t.fillInStackTrace())
                api_response.setValue(null)
            }
        })
        return api_response
    }

    suspend fun getSubbreedPhoto(breed: String?, breeds: List<String?>): MutableLiveData<List<String>>? {
        val apiDataService: ApiDataService = retrofit.create(
            ApiDataService::class.java
        )
        val api_response = MutableLiveData<List<String>>()
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
                    System.out.println(imageData.toString())
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

private fun <T> MutableLiveData<T>.setValue(message: ArrayList<String?>?) {

}
