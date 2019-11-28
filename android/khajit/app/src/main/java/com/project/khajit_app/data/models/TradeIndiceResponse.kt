package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class TradeIndiceResponse (
    @SerializedName("majorIndexesList")
    val majorIndexesList: List<TradeIndice>
)

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