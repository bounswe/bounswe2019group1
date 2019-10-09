package com.project.khajit_app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.project.khajit_app.R

class LoginPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}
    }

    fun goToRegister(view: View) {
        startActivity(Intent(this, SignUpPageActivity::class.java))
    }
}