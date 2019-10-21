package com.project.khajit_app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import com.project.khajit_app.global.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpPageActivity : AppCompatActivity() {

    lateinit var email_input : EditText
    lateinit var password_input : EditText
    lateinit var repeat_password_input : EditText
    lateinit var first_name_input : EditText
    lateinit var last_name_input : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}

        Toast.makeText(applicationContext, "WELCOME TO THE SIGN UP", Toast.LENGTH_LONG).show()

        email_input = findViewById(R.id.input_email)
        password_input = findViewById(R.id.input_password)
        repeat_password_input = findViewById(R.id.input_repassword)
        first_name_input =findViewById(R.id.input_first_name)
        last_name_input = findViewById(R.id.input_last_name)

        var register_button =  findViewById<Button>(R.id.register_as_basic_button)
        register_button.setOnClickListener(registerBasic)

    }

    private val registerBasic = View.OnClickListener { view ->


        var email_information = email_input.text.toString().trim()
        var username_information = ""
        var password_information = password_input.text.toString().trim()
        var repassword_information = repeat_password_input.text.toString().trim()
        var firstname_information = first_name_input.text.toString().trim()
        var lastname_information = last_name_input.text.toString().trim()

        if(email_information.isEmpty()){
            email_input.error = "Email is required."
            email_input.requestFocus()
            return@OnClickListener
        }
        if(password_information.isEmpty()){
            password_input.error = "Email is required."
            password_input.requestFocus()
            return@OnClickListener
        }
        if(repassword_information.isEmpty()){
            repeat_password_input.error = "Email is required."
            repeat_password_input.requestFocus()
            return@OnClickListener
        }
        if(firstname_information.isEmpty()){
            first_name_input.error = "Fİrst name is required."
            first_name_input.requestFocus()
            return@OnClickListener
        }
        if(lastname_information.isEmpty()){
            last_name_input.error = "Last name is required."
            last_name_input.requestFocus()
            return@OnClickListener
        }
        if(!password_information.equals(repassword_information)){
            repeat_password_input.error = "Passwords are not the same."
            repeat_password_input.requestFocus()
            return@OnClickListener
        }

        username_information = email_information
        val userInfo = BasicUser(username_information,email_information,firstname_information,lastname_information,password_information)
        RetrofitClient.instance.createBasicUser(userInfo).enqueue(object : Callback<BasicRegisterResponse>{
            override fun onResponse(
                call: Call<BasicRegisterResponse>,
                response: Response<BasicRegisterResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.username != null){
                        Toast.makeText(applicationContext, "interesting", Toast.LENGTH_LONG).show()
                        //Toast.makeText(applicationContext,response.body()?.username.toString(), Toast.LENGTH_LONG).show()

                    }else{
                        println(response)
                        println(call)
                        println(response.body()?.toString()?.trim())
                        Toast.makeText(applicationContext,"User has been created",Toast.LENGTH_LONG).show()
                        Log.d("success:", "" + response.body()?.user?.username)

                        startActivity(Intent(this@SignUpPageActivity, LoginPageActivity::class.java))
                        /*
                        response.body()?.token.let {
                            User.token = it
                        }

                         */
                    }
                }else{
                    Toast.makeText(applicationContext,"Invalid or used email",Toast.LENGTH_LONG).show()
                    Log.d("error message:", response.message())
                }
            }

            override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
            }

        }
        )
    }

}

