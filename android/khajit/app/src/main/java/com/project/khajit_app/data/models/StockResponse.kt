package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class StockResponse (
    val stockList: List<Stock?>
)

data class Stock (
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("price")
    val price: String
)