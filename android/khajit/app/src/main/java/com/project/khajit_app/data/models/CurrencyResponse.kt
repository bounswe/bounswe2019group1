package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class CurrencyResponse (
    @SerializedName("EUR")
    val value_eur: String,
    @SerializedName("GBP")
    val value_gbp: String,
    @SerializedName("TRY")
    val value_try: String
)