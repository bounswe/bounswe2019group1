package com.project.khajit_app.data.models

import android.text.SpannableString
import java.io.Serializable

data class GeneralArticleModel(val id : Int?,
                               val title: String?,
                               var content : String?,
                               val author : UserAllInfo?,
                               val is_public : Boolean?,
                               val created_date : String?,
                               val image : String?): Serializable

