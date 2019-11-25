package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class CryptoResponse (
    @SerializedName("BTC")
    val value_btc: String,
    @SerializedName("ETH")
    val value_eth: String,
    @SerializedName("LTC")
    val value_ltc: String
)