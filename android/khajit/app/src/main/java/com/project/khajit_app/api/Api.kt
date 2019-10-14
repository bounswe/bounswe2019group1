package com.project.khajit_app.api

import com.project.khajit_app.data.models.BasicRegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("user/registerbasic/")
    fun createBasicUser(
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("email") email : String,
        @Field("first_name") first_name : String,
        @Field("last_name") last_name : String


        ):Call<BasicRegisterResponse>
}