package com.project.khajit_app.data.models

import java.io.Serializable

data class GeneralArticleModel(val id : Int?,
                               val title: String?,
                               val content : String?,
                               val author : UserAllInfo?,
                               val is_public : Boolean?,
                               val created_date : String?,
                               val image : String?): Serializable

