package com.project.khajit_app.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    //private val AUTH = "Basic"
    private const val BASE_URL = "http://35.163.120.227:8000/"    //dynamic ip adresi girilmesi lazım

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