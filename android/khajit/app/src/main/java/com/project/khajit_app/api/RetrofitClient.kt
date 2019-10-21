package com.project.khajit_app.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress.getByAddress

object RetrofitClient {

    //private val AUTH = "Basic"
    //var ipAddress = Inet4Address.getLocalHost().hostAddress
    private const val BASE_URL = "http://192.168.1.2:8000/"    //dynamic ip adresi girilmesi lazım

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type","application/json")
                    .method(original.method(),original.body())

                val request = requestBuilder.build()
                chain.proceed(request)

            }.build()

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api ::class.java)
    }

}