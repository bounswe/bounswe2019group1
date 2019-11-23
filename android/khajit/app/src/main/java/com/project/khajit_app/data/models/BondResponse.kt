package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class BondResponse (
    @SerializedName("B1")
    val value_1: String,
    @SerializedName("B2")
    val value_2: String,
    @SerializedName("B3")
    val value_3: String
)