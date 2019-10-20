package com.project.khajit_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.EditText
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

    fun changePersonalInfo(view: View) {
        var first_name = findViewById<EditText>(R.id.input_first_name)
        var last_name = findViewById<EditText>(R.id.input_last_name)
        var title = findViewById<EditText>(R.id.input_title)
        var bio = findViewById<EditText>(R.id.text_bio_edit)

        if(first_name.text.isEmpty()){
            first_name.error = "First name is required."
            first_name.requestFocus()
            return
        }
        if(first_name.text.length > 20) {
            first_name.error = "Max length of first name should not exceed 20"
            first_name.requestFocus()
            return
        }
        if(last_name.text.isEmpty()){
            last_name.error = "Last name is required."
            last_name.requestFocus()
            return
        }
        if(last_name.text.length > 20) {
            last_name.error = "Max length of last name should not exceed 20"
            last_name.requestFocus()
            return
        }
        if(title.text.length > 20) {
            title.error = "Max length of title should not exceed 20"
            title.requestFocus()
            return
        }

        // REQUEST HERE

    }

    fun changePasswordInfo(view: View) {
        var old_pw = findViewById<EditText>(R.id.input_old_password)
        var new_pw = findViewById<EditText>(R.id.input_new_password)
        var re_new_pw = findViewById<EditText>(R.id.input_re_new_password)

        if(old_pw.text.isEmpty()){
            old_pw.error = "Current password is required."
            old_pw.requestFocus()
            return
        }
        if(new_pw.text.isEmpty()){
            new_pw.error = "New password is required."
            new_pw.requestFocus()
            return
        }
        if(re_new_pw.text.isEmpty()){
            re_new_pw.error = "Repeating new password is required."
            re_new_pw.requestFocus()
            return
        }
        if(new_pw.text.length < 6 || new_pw.text.length > 20){
            new_pw.error = "Password length should be in the range of 6-20"
            new_pw.requestFocus()
            return
        }
        if(new_pw.text.toString() != re_new_pw.text.toString()) {
            re_new_pw.error = "Two passwords should match"
            re_new_pw.requestFocus()
            return
        }
        if(old_pw.text.toString() == new_pw.text.toString()){
            new_pw.error = "New password should be different from previous password"
            new_pw.requestFocus()
            return
        }

        // REQUEST GOES HERE, do not forget to check old password validity

    }
}
