package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class CommodityResponse (
    @SerializedName("XAG")
    val value_xag: String,
    @SerializedName("XAU")
    val value_xau: String,
    @SerializedName("XRH")
    val value_xrh: String
)