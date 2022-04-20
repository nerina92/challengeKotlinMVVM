package com.example.challengemaxisistemaskotlin.data.network

import okhttp3.*
import java.net.ProtocolException
import java.net.SocketTimeoutException

class TimeOutInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response

        try {
            response = chain.proceed(chain.request())

        }catch(exception: SocketTimeoutException){
            val responseString = exception.message.toString()
            response = Response.Builder()
                .code(408)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(
                    ResponseBody.create(MediaType.get("application/json"),responseString)
                )
                .addHeader("content-type", "application/json")
                .build()
        }catch(exception: ProtocolException){
            val responseString = exception.message.toString()
            response = Response.Builder()
                .code(400)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(
                    ResponseBody.create(MediaType.get("application/json"),responseString)
                )
                .addHeader("content-type", "application/json")
                .build()
        }catch(exception: Exception){
            val responseString = exception.message.toString()
            response = Response.Builder()
                .code(400)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(
                    ResponseBody.create(MediaType.get("application/json"),responseString)

                )
                .addHeader("content-type", "application/json")
                .build()
        }

        return response
    }
}