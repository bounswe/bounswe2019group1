package com.project.khajit_app.data.models

data class ArticleCommentItem(val id: Int,
                              val text : String,
                              val user :  UserAllInfo?,
                              val article : Int,
                              val created_date : String)