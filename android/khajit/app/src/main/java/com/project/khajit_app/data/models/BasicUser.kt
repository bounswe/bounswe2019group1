package com.project.khajit_app.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BasicUser ( val username : String, val email : String, val first_name : String, val last_name : String, val password : String, val is_public : Boolean) : Parcelable