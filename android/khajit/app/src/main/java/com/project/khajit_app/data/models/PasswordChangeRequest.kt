package com.project.khajit_app.data.models

data class PasswordChangeRequest( val oldPasswordInfo : String, val newPasswordInfo : String, val userTokenInfo : String)