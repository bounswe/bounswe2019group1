package com.project.khajit_app.api

import com.project.khajit_app.data.annotationModels.*
import retrofit2.Call
import retrofit2.http.*

interface AnnotationApi {


    @Headers("Content-Type: application/json")
    @POST("annotation/creatorregister/")
    fun createCreator(@Body body: CreatorModel): Call<CreatorModel>

    @Headers("Content-Type: application/json")
    @GET("annotation/creatorlistmobile/")
    fun getCreatorList():Call<CreatorListModel>


    @Headers("Content-Type: application/json")
    @GET("/annotation/creatorexist/?")
    fun isCreatorExists(@Query("id") id : String ):Call<CreatorExistsResponse>

    @Headers("Content-Type: application/json")
    @PUT("annotation/addbody/")
    fun addBodyToAnnotation(@Body body: AddBodyModel):Call<AnnotationModelResponse>


    @Headers("Content-Type: application/json")
    @GET("annotation/getannotationsmobile/?")
    fun getAnnotationsBySource(@Query("source") source : String ):Call<GetAnnotationModelResponse>

    @Headers("Content-Type: application/json")
    @HTTP(method="DELETE", path="annotation/deleteannotation/", hasBody=true)
    fun deleteAnnotation(@Body body: DeleteAnnotationModel?):Call<DeleteAnnotationResponse>

    /*@Headers("Content-Type: application/json")
    @GET("article/listArticleByUserId/{id}/")
    fun checkCreatorExists(@Path(value = "id", encoded = true) userId: Int):Call<CreatorExistsResponse>*/
}