package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class DepositFundsResponse (
    @SerializedName("USD")
    val current_wallet_balance: String
)