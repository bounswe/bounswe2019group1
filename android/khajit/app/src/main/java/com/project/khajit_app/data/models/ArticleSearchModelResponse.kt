package com.project.khajit_app.data.models


data class ArticleSearchModelResponse(val id: Int?,
                                      val title:String?,
                                      val content : String?,
                                      val is_public : Boolean?,
                                      val author : UserAllInfo?,
                                      val created_date : String?,
                                      val image: String?)

