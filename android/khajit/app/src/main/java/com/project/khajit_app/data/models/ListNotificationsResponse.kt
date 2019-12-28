package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class ListNotificationsResponse (
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: ArrayList<GeneralNotificationModel?>
)