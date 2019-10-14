package com.project.khajit_app.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpPageActivity : AppCompatActivity(), OnClickListener {


    lateinit var email_input : EditText
    lateinit var password_input : EditText
    lateinit var repeat_password_input : EditText
    lateinit var first_name_input : EditText
    lateinit var last_name_input : EditText
    lateinit var trader_button : Button
    lateinit var basic_user_register :Button
    lateinit var google_user_register : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}


        email_input = findViewById(R.id.input_email)
        password_input = findViewById(R.id.input_password)
        repeat_password_input = findViewById(R.id.input_repassword)
        trader_button = findViewById(R.id.register_as_trader_button) as Button
        basic_user_register = findViewById(R.id.register_as_basic_button)
        google_user_register = findViewById(R.id.register_with_google_button)
        first_name_input =findViewById(R.id.input_first_name)
        last_name_input = findViewById(R.id.input_last_name)


        basic_user_register.setOnClickListener{
            var email_information = email_input.text.toString().trim()
            var username_information = ""
            var password_information = password_input.text.toString().trim()
            var repassword_information = repeat_password_input.text.toString().trim()
            var firstname_information = first_name_input.text.toString().trim()
            var lastname_information = last_name_input.text.toString().trim()

            if(email_information.isEmpty()){
                email_input.error = "Email is required."
                email_input.requestFocus()
                return@setOnClickListener
            }
            if(password_information.isEmpty()){
                password_input.error = "Email is required."
                password_input.requestFocus()
                return@setOnClickListener
            }
            if(repassword_information.isEmpty()){
                repeat_password_input.error = "Email is required."
                repeat_password_input.requestFocus()
                return@setOnClickListener
            }
            if(firstname_information.isEmpty()){
                first_name_input.error = "Fİrst name is required."
                first_name_input.requestFocus()
                return@setOnClickListener
            }
            if(lastname_information.isEmpty()){
                last_name_input.error = "Last name is required."
                last_name_input.requestFocus()
                return@setOnClickListener
            }
            if(!password_information.equals(repassword_information)){
                repeat_password_input.error = "Passwords are not the same."
                repeat_password_input.requestFocus()
                return@setOnClickListener
            }

            username_information = email_information

            RetrofitClient.instance.createBasicUser(username_information,password_information,email_information,firstname_information,lastname_information)
                .enqueue(object : Callback<BasicRegisterResponse>{
                    override fun onResponse(
                        call: Call<BasicRegisterResponse>,
                        response: Response<BasicRegisterResponse>
                    ) {
                        Toast.makeText(applicationContext,response.body()?.token,Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                }

                )
        }
/*

        trader_button.setOnClickListener {
            val intent = Intent(this@SignUpPageActivity,SignUpPageTraderActivity::class.java)
            var email_information = email_input.text.toString().trim()
            var password_information = password_input.text.toString().trim()
            var repassword_information = repeat_password_input.text.toString().trim()

            if(email_information.isEmpty()){
                email_input.error = "Email is required"
                email_input.requestFocus()
                return@setOnClickListener
            }
            if(password_information.isEmpty()){
                password_input.error = "Email is required"
                password_input.requestFocus()
                return@setOnClickListener
            }
            if(repassword_information.isEmpty()){
                repeat_password_input.error = "Email is required"
                repeat_password_input.requestFocus()
                return@setOnClickListener
            }


           // intent.putExtra("Username", userName)
           // intent.putExtra("Password", password)
            startActivity(intent);
        }
*/


    }

    override fun onClick(v: View?) {
        var email_information = email_input.text.toString().trim()
        var password_information = password_input.text.toString().trim()
        var repassword_information = repeat_password_input.text.toString().trim()

        if(email_information.isEmpty()){
            email_input.error = "Email is required"
            //Toast.makeText(SignUpPageActivity, "Error Msg", Toast.LENGTH_SHORT).show()
        }

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun completeRegistrationAsGoogle(view: View) {
        // TODO
    }

    fun switchUserType(view: View) {

        if(findViewById<TextInputLayout>(R.id.layout_card_ccv).visibility == View.GONE) {
            findViewById<TextInputLayout>(R.id.layout_card_ccv).visibility = View.VISIBLE
            findViewById<TextInputLayout>(R.id.layout_card_expire).visibility = View.VISIBLE
            findViewById<TextInputLayout>(R.id.layout_card_number).visibility = View.VISIBLE
            findViewById<TextInputLayout>(R.id.layout_card_owner).visibility = View.VISIBLE
            findViewById<Button>(R.id.layout_info).visibility = View.VISIBLE
            findViewById<Button>(R.id.register_as_trader_button).text = "Register as Basic"
        }else {
            findViewById<TextInputLayout>(R.id.layout_card_ccv).visibility = View.GONE
            findViewById<TextInputLayout>(R.id.layout_card_expire).visibility = View.GONE
            findViewById<TextInputLayout>(R.id.layout_card_number).visibility = View.GONE
            findViewById<TextInputLayout>(R.id.layout_card_owner).visibility = View.GONE
            findViewById<Button>(R.id.layout_info).visibility = View.GONE
            findViewById<Button>(R.id.register_as_trader_button).text = "Register as Trader"
        }
    }

}