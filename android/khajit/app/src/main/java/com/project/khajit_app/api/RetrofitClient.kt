package com.project.khajit_app.api

import com.project.khajit_app.global.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress.getByAddress

object RetrofitClient {

    //private val AUTH = "Basic"

    //var ipAddress = Inet4Address.getLocalHost().hostAddress
    private const val BASE_URL = "http://35.163.120.227:8000/"    //dynamic ip adresi girilmesi lazım
    private const val BASE_URL_ANNOTATION = "http://35.163.120.227:8020/"    //dynamic ip adresi girilmesi lazım


    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type","application/json")
                    .method(original.method(),original.body())

                if(User.token == "") {

                }
                else {
                    requestBuilder.addHeader("Authorization", "JWT %s".format(User.token))
                }
                /*
                User.token.let {
                    requestBuilder.addHeader("Authorization", "JWT %s".format(User.token))
                }
                 */


                val request = requestBuilder.build()
                chain.proceed(request)
            }.build()

    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api ::class.java)
    }
    val instanceAnnotation: AnnotationApi by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_ANNOTATION)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(AnnotationApi ::class.java)
    }
}