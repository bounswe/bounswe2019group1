package com.project.khajit_app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.project.khajit_app.R

class MainPageActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}


        this.viewpager = findViewById(R.id.viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter

    }

    fun goToRegister(view : View) {
        startActivity(Intent(this, SignUpPageActivity::class.java))
    }

    fun goToLogin(view : View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }
}