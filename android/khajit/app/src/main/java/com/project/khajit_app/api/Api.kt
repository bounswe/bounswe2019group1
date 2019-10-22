package com.project.khajit_app.api

import com.project.khajit_app.data.models.*

import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.*

interface Api {

    @Headers("Content-Type: application/json")
    @POST("user/registerbasic/")
    fun createBasicUser(@Body body: BasicUser?):Call<BasicRegisterResponse>

    @Headers("Content-Type: application/json")
    @POST("user/registertrader/")
    fun createTraderUser(@Body body: TraderUser):Call<BasicRegisterResponse>  //trader user response yapılması lazım

    @Headers("Content-Type: application/json")
    @POST("user/login/")
    fun loginUser(@Body body: userToBeLogin?):Call<LoginResponse>

    @Headers("Content-Type: application/json")
    //@GET("user/retrievemobile/{id}")
    @GET
    fun getInfo(@Url url: String?):Call<UserAllInfo>
    //fun getInfo(@Body body: UserInfoGet?):Call<UserAllInfo>

}