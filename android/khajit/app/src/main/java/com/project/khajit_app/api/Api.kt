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
    fun getInfo(@Path(value = "id", encoded = true) userId: String):Call<GenericUserModel>
    //fun getInfo(@Body body: UserInfoGet?):Call<UserAllInfo>

    @Headers("Content-Type: application/json")
    @GET("follow/listFollowing/")
    fun followingList():Call<GeneralFollowModel>

    @Headers("Content-Type: application/json")
    @GET("follow/listFollower/")
    fun followerList():Call<GeneralFollowModel2>

    @Headers("Content-Type: application/json")
    @GET("follow/listFollowingWithIdFront/{id}")
    fun followingListID(@Path(value = "id", encoded = true) userId: String):Call<GeneralFollowModel>

    @Headers("Content-Type: application/json")
    @GET("follow/listFollowerWithIdFront/{id}")
    fun followerListID(@Path(value = "id", encoded = true) userId: String):Call<GeneralFollowModel2>

    @Headers("Content-Type: application/json")
    @PUT("user/updateuser/")
    fun updateUser(@Body body: UpdateUser):Call<UpdateUserResponse>

    @Headers("Content-Type: application/json")
    @PUT("user/updateuser/")
    fun upgrade_downgrade(@Body body: UpgradeDowngrade):Call<UpdateUserResponse>

    @Headers("Content-Type: application/json")
    @PUT("user/updateuser/")
    fun changePrivacy(@Body body: ChangePrivacy):Call<UpdateUserResponse>

    @Headers("Content-Type: application/json")
    @POST("user/search_user/")
    fun searchUsername(@Body body: SearchRequest):Call<SearchResponse>

    // TODO
    @Headers("Content-Type: application/json")
    @PUT("user/updatepass/")
    fun changePassword(@Body body: PasswordChange):Call<GenericUserModel>

    @Headers("Content-Type: application/json")
    @PUT("user/updateuser/")
    fun changeIban(@Body body: UpgradeDowngrade):Call<UpdateUserResponse>

    @Headers("Content-Type: application/json")
    @PUT("user/userupgrade/")
    fun upgradeUser():Call<GenericUserModel>

    @Headers("Content-Type: application/json")
    @PUT("user/userdowngrade/")
    fun downgradeUser():Call<GenericUserModel>

    @Headers("Content-Type: application/json")
    @GET("follow/isFollowingFront/{id}/")
    fun isFollowing(@Path(value = "id", encoded = true) userId: String):Call<isFollowingResponseModel>

    @Headers("Content-Type: application/json")
    @POST("follow/follow/")
    fun followUser(@Body body: FollowIDModel?):Call<FollowIDModelResponse>

    @Headers("Content-Type: application/json")
    @HTTP(method="DELETE", path="follow/delete/", hasBody=true)
    fun unfollowUser(@Body body: FollowIDModel?):Call<FollowIDModelResponse>

    @Headers("Content-Type: application/json")
    @GET("equipment/currency/")
    fun currencyValues():Call<CurrencyResponse>

    @Headers("Content-Type: application/json")
    @GET("equipment/metalcurrency/")
    fun commodityValues():Call<CommodityResponse>

    @Headers("Content-Type: application/json")
    @GET("equipment/cryptocurrency/")
    fun cryptoValues():Call<CryptoResponse>

    @Headers("Content-Type: application/json")
    @GET("equipment/etfs/")
    fun etfValues():Call<ETFResponse>

    @Headers("Content-Type: application/json")
    @GET("equipment/stock/")
    fun stockValues():Call<List<Stock>>

    @Headers("Content-Type: application/json")
    @GET("equipment/traceindices/")
    fun tradeValues():Call<TradeIndiceResponse>

}