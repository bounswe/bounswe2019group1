package com.project.khajit_app.api

import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @Headers("Content-Type: application/json")
    @POST("user/registerbasic/")
    fun createBasicUser(@Body body: BasicUser):Call<BasicRegisterResponse>
}