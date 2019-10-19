package com.project.khajit_app.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.mikhaellopez.circularimageview.CircularImageView
import com.project.khajit_app.R

class ProfilePageActivity : AppCompatActivity() {

    var equipments = arrayOf(
        "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Max OS X", "Max OS X")
    var rates = arrayOf(
        "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}

        findViewById<TextView>(R.id.text_bio).movementMethod = ScrollingMovementMethod()

        // This will be used for further methods in order to set prediction rates
        var lview =  findViewById<ListView>(R.id.list_prediction_name)
        var ladapter = ListViewAdapter(this, equipments, rates)
        lview.adapter = ladapter

    }
}