package com.project.khajit_app.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class OneEventResponse (val title: String, val country: String, val date: String, val impact: String, val forecast: String, val previous: String)