package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class StockResponse (
    @SerializedName("GOOGL")
    val value_googl: String,
    @SerializedName("AAPL")
    val value_aapl: String,
    @SerializedName("GM")
    val value_gm: String
)