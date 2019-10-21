package com.project.khajit_app.api

import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import com.project.khajit_app.data.models.LoginResponse
import com.project.khajit_app.data.models.UserLogin
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @Headers("Content-Type: application/json")
    @POST("user/registerbasic/")
    fun createBasicUser(@Body body: BasicUser):Call<BasicRegisterResponse>

    @Headers("Content-Type: application/json")
    @POST("user/login/")
    fun loginUser(@Body body: UserLogin):Call<LoginResponse>

  /*  @Headers("Content-Type: application/json")
    @POST("user/registertrader/")
    fun createTraderUser(@Body body: TraderUser):Call<TraderRegisterResponse>*/

}