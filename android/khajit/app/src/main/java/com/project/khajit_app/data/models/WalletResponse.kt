package com.project.khajit_app.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WalletResponse (val id: Int, val USD: String, val Sent_USD: String, val Wealth_USD: String, val BTC: String, val ETH: String, val LTC: String, val XAG: String, val XAU: String, val GOOGL: String, val AAPL: String, val GM: String, val EUR: String, val GBP: String, val TRY: String, val SPY: String, val IVV: String, val VTI: String, val DJI: String, val IXIC: String, val INX: String, val owner: Int, val detail: String?) : Parcelable