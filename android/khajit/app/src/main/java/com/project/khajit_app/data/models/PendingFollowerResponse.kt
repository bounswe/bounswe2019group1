package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PendingFollowerResponse(
    @SerializedName("list")
    val list : List<FollowModel3?>

): Serializable

data class FollowModel3(val id: Int,
                        val follower: GenericUserModel,
                        val following: Int,
                        val is_active : Boolean?)