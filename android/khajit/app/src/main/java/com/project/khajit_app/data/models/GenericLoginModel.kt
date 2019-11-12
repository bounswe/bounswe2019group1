package com.project.khajit_app.data.models

data class GenericLoginModel( val id: Int,
                             val  username: String,
                             val email: String,
                             val first_name: String,
                             val last_name: String,
                             val groups: List<String>?)