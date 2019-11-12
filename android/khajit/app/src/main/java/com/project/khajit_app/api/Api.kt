package com.project.khajit_app.api

import com.project.khajit_app.data.models.*
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
    @GET("user/retrieve/{id}")
    fun getInfo(@Path(value = "id", encoded = true) userId: String):Call<UserAllInfo>
    //fun getInfo(@Body body: UserInfoGet?):Call<UserAllInfo>

    @Headers("Content-Type: application/json")
    @GET("follow/listFollowing/")
    fun followingList():Call<List<FollowingModel>>

    @Headers("Content-Type: application/json")
    @GET("follow/listFollower/")
    fun followerList():Call<List<FollowerModel>>

    @Headers("Content-Type: application/json")
    @PUT("user/updateuser/")
    fun updateUser(@Body body: UpdateUser):Call<UpdateUserResponse>

    @Headers("Content-Type: application/json")
    @POST("user/search_user/")
    fun searchUsername(@Body body: SearchRequest):Call<SearchResponse>

    // TODO
    @Headers("Content-Type: application/json")
    @PUT("user/updatepass/")
    fun changePassword(@Body body: PasswordChange):Call<GenericUserModel>
}