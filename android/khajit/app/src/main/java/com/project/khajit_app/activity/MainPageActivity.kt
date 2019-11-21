package com.project.khajit_app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.project.khajit_app.R
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class MainPageActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}



    }

    fun goToRegister(view : View) {
        startActivity(Intent(this, SignUpPageActivity::class.java))
    }

    fun goToLogin(view : View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }
    fun goToHomePage(view : View) {
        startActivity(Intent(this, HomeFeedPageGuestActivity::class.java))
    }

    companion object {
        fun getLaunchIntent(from : Context) = Intent(from, MainPageActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onBackPressed() {}

}