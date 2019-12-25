package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class ETFResponse (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("SPY")
    val obj_spy: String?,
    @SerializedName("IVV")
    val obj_ivv: String?,
    @SerializedName("VTI")
    val obj_vti: String?
)

/*  old version

data class ETFResponse (
    @SerializedName("SPY")
    val obj_spy: ETF,
    @SerializedName("IVV")
    val obj_ivv: ETF,
    @SerializedName("VTI")
    val obj_vti: ETF
)
*/


data class ETF (
    @SerializedName("id")
    val id: String,

    @SerializedName("mobile_title")
    val mobile_title: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("assets")
    val assets: String,

    @SerializedName("average_volume")
    val average_volume: String,

    @SerializedName("ytd")
    val ytd: String
)