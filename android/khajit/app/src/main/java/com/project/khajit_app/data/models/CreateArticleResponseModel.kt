package com.project.khajit_app.data.models


data class CreateArticleResponseModel( val id: Int?,
                                       val title:String?,
                                       val content : String?,
                                       val is_public : Boolean?,
                                       val author : Int,
                                       val created_date : String?,
                                       val detail : String? )

