package com.project.khajit_app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.project.khajit_app.R
import kotlinx.coroutines.*

class SignUpPageActivity : AppCompatActivity() {

    var basicUser = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}

    }

    suspend fun main() = coroutineScope {
        launch {
            delay(1000)
            Toast.makeText(applicationContext, "123123123123", Toast.LENGTH_LONG).show()
            delay(1000)
        }
        Toast.makeText(applicationContext, "asdasdasd", Toast.LENGTH_LONG).show()
    }

    fun fading(): Boolean {
        return true
    }
    fun switchUserType(view: View) {

        /*
        val container = findViewById(R.id.root) as ScrollView
        val fade = Fade()
        fade.setDuration(1000)

        GlobalScope.launch {
            suspend {
                TransitionManager.beginDelayedTransition(container, fade)
                withContext(Dispatchers.Main) {

                }
            }.invoke()
        }
        */
        if(basicUser) {
            findViewById<TextInputLayout>(R.id.layout_card_ccv).visibility = View.VISIBLE
            findViewById<TextInputLayout>(R.id.layout_card_expire).visibility = View.VISIBLE
            findViewById<TextInputLayout>(R.id.layout_card_number).visibility = View.VISIBLE
            findViewById<TextInputLayout>(R.id.layout_card_owner).visibility = View.VISIBLE
            findViewById<Button>(R.id.button5).visibility = View.VISIBLE
            findViewById<Button>(R.id.button_switch).text = "Register as Basic"
            this.basicUser = false
        }else {
            findViewById<TextInputLayout>(R.id.layout_card_ccv).visibility = View.GONE
            findViewById<TextInputLayout>(R.id.layout_card_expire).visibility = View.GONE
            findViewById<TextInputLayout>(R.id.layout_card_number).visibility = View.GONE
            findViewById<TextInputLayout>(R.id.layout_card_owner).visibility = View.GONE
            findViewById<Button>(R.id.button5).visibility = View.GONE
            findViewById<Button>(R.id.button_switch).text = "Register as Trader"
            this.basicUser = true
        }
    }

    fun goToLogin(view: View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }

    fun completeRegistration(view: View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }

    fun completeRegistrationAsGoogle(view: View) {

    }

}