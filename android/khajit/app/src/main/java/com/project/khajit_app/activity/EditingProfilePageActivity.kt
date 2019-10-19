package com.project.khajit_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.project.khajit_app.R

class EditingProfilePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editing_profile_page)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}

        findViewById<TextView>(R.id.text_bio_edit).movementMethod = ScrollingMovementMethod()
    }
}
