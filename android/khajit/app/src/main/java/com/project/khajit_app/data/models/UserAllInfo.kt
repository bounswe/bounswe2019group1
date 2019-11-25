package com.project.khajit_app.data.models


data class UserAllInfo(val ss: List<String>?, val id: Int, val username: String, val first_name: String, val last_name: String, val email: String,
                      val location: String, val phone_number: String, val iban_number: String, val citizenship_number: String,
                       val biography: String, val title: String, val last_changed_password_date: String)