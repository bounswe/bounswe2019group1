package com.project.khajit_app.data.models

data class GenericUserModel( val id: Int,
   val  username: String,
val email: String,
val first_name: String,
val last_name: String,
val location: String,
val phone_number: Int,
val iban_number: Int,
val citizenship_number: String,
val biography: String,
val title: String,
val last_changed_password_date:String)