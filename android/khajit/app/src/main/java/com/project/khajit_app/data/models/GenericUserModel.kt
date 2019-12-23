package com.project.khajit_app.data.models

import com.google.gson.annotations.SerializedName

data class GenericUserModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("iban_number")
    val iban_number: String,
    @SerializedName("citizenship_number")
    val citizenship_number: String,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("last_changed_password_date")
    val last_changed_password_date: String,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("is_public")
    val is_public: Boolean,
    @SerializedName("groups")
    val groups: List<String>,
    val detail: String?)