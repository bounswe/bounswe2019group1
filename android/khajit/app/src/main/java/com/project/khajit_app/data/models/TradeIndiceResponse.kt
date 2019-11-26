package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class TradeIndiceResponse (
    @SerializedName("DJI")
    val value_dji: String,
    @SerializedName("IXIC")
    val value_ixic: String,
    @SerializedName("INX")
    val value_inx: String
)

//below is deprecated
data class TradeIndice (
    @SerializedName("ticker")
    val id: String,

    @SerializedName("changes")
    val mobile_title: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("indexName")
    val assets: String

)