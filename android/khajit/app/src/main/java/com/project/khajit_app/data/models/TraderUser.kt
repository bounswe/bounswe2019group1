package com.project.khajit_app.data.models

data class TraderUser(val username: String, val email: String, val first_name: String, val last_name: String, val password: String,
                 val location: String, val iban_number: String, val is_public: Boolean)