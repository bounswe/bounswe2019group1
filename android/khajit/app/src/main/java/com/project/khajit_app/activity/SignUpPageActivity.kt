package com.project.khajit_app.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.project.khajit_app.R
import com.project.khajit_app.data.models.BasicUser
import com.project.khajit_app.global.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpPageActivity : AppCompatActivity(), OnClickListener {



    lateinit var email_input : EditText
    lateinit var password_input : EditText
    lateinit var repeat_password_input : EditText
    lateinit var first_name_input : EditText
    lateinit var last_name_input : EditText
    lateinit var user_register_basic_info_button :Button
    lateinit var google_user_register : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}



    }



    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun completeRegistrationAsGoogle(view: View) {
        // TODO
    }
    fun goToSignUpPageTraderActivity(view : View) {

        val intent = Intent(this@SignUpPageActivity,SignUpPageTraderActivity::class.java);


        email_input = findViewById(R.id.input_email)
        password_input = findViewById(R.id.input_password)
        repeat_password_input = findViewById(R.id.input_repassword)
        first_name_input =findViewById(R.id.input_first_name)
        last_name_input = findViewById(R.id.input_last_name)

        //It must be revised : in android it needs to be pressed buttons twice -> error
        user_register_basic_info_button = findViewById(R.id.register_as_basic_button)
        google_user_register = findViewById(R.id.register_with_google_button)


        user_register_basic_info_button.setOnClickListener{


            var email_information = email_input.text.toString().trim()
            var username_information = ""
            var password_information = password_input.text.toString().trim()
            var repassword_information = repeat_password_input.text.toString().trim()
            var firstname_information = first_name_input.text.toString().trim()
            var lastname_information = last_name_input.text.toString().trim()


            //TO-DO password requirements
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
                first_name_input.error = "First name is required."
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
            val userInfo = BasicUser(username_information,email_information,firstname_information,lastname_information,password_information)

            RetrofitClient.instance.createBasicUser(userInfo)
                .enqueue(object : Callback<BasicRegisterResponse>{
                    override fun onResponse(
                        call: Call<BasicRegisterResponse>,
                        response: Response<BasicRegisterResponse>
                    ) {
                        if(response.isSuccessful ){
                            if(response.body()?.username != null){
                                Toast.makeText(applicationContext,response.body()?.username.toString(),Toast.LENGTH_LONG).show()
                            }else{
                                println(response)
                                println(call)
                                println(response.body()?.toString()?.trim())
                                Toast.makeText(applicationContext,"oldu",Toast.LENGTH_LONG).show()
                                Log.d("success:", "" + response.body()?.user?.username)

                                response.body()?.token.let {
                                    User.token = it
                                }

                            }

                        }else{
                            Log.d("error message:", response.message())
                        }

                    }

                    override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                }

                )

            intent.putExtra("userInfo", userInfo)
            intent.putExtra("googleUser",0)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()

        }

    }



}

