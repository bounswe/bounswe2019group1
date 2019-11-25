package com.project.khajit_app.api

import com.project.khajit_app.global.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress.getByAddress
import java.util.concurrent.TimeUnit

object RetrofitClient {

    //private val AUTH = "Basic"

    //var ipAddress = Inet4Address.getLocalHost().hostAddress
    private const val BASE_URL = "http://35.163.120.227:8000/"    //dynamic ip adresi girilmesi lazım

    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

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
            }
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()


    val instance: Api by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api ::class.java)
    }

}