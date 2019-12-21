package com.project.khajit_app.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class ListEventResponse (val count: Int, val results: List<OneEventResponse>)