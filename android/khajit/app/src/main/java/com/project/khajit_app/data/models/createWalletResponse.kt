package com.project.khajit_app.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class createWalletResponse (val detail : String?, val id: Int?, val owner: Int?) : Parcelable