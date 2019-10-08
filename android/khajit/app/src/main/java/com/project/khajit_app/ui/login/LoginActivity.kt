package com.project.khajit_app.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.viewpager.widget.ViewPager

import com.project.khajit_app.R

class LoginActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}
        setContentView(R.layout.activity_login)

        this.viewpager = findViewById(R.id.viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter


    }
}