package com.project.khajit_app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import com.project.khajit_app.data.models.TraderUser
import com.project.khajit_app.data.models.createWalletResponse
import com.project.khajit_app.global.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpPageTraderActivity : AppCompatActivity() {
    private lateinit var iban_input: EditText
    private lateinit var trader_button: Button
    private lateinit var basic_user_register: Button
    private lateinit var userInfo: BasicUser
    private var googleUser: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page_trader)


        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Parcelable Data
                userInfo = intent.getParcelableExtra("userInfo") as BasicUser
            }
            bundle.apply {
                googleUser = intent.getSerializableExtra("googleUser") as Int
            }


        }
        basic_user_register = findViewById(R.id.register_as_basic_button_second)
        iban_input = findViewById(R.id.input_iban)
        trader_button = findViewById(R.id.register_as_trader_button_first)

        trader_button.setOnClickListener { root ->
            goToMainTraderUserActivity(root)
        }

        basic_user_register.setOnClickListener { root ->
            goToMainBasicUserActivity(root)
        }
    }

    fun goToMainTraderUserActivity(view: View) {

        var iban_information = iban_input.text.toString()

        if (iban_information.length != 16) {
            iban_input.error = "IBAN lenght should be 16."
            iban_input.requestFocus()
            return
        }

        val traderUserInfo = TraderUser(
            userInfo.username,
            userInfo.username,
            userInfo.first_name,
            userInfo.last_name,
            userInfo.password,
            userInfo.location,
            iban_information,
            true
        )

        RetrofitClient.instance.createTraderUser(traderUserInfo)
            .enqueue(object : Callback<BasicRegisterResponse> {
                override fun onResponse(
                    call: Call<BasicRegisterResponse>,
                    response: Response<BasicRegisterResponse>
                ) {
                    println(response.toString())
                    if (response.code() == 200) {
                        if (response.body()?.username != null) {
                            Toast.makeText(
                                applicationContext,
                                "Something went wrong!",
                                Toast.LENGTH_LONG
                            ).show()
                            //Toast.makeText(applicationContext,response.body()?.username.toString(), Toast.LENGTH_LONG).show()

                        } else {
                            User.token = response.body()!!.token
                            RetrofitClient.instance.createWallet().enqueue(object :
                                Callback<createWalletResponse> {
                                override fun onResponse(
                                    call: Call<createWalletResponse>,
                                    response: Response<createWalletResponse>
                                ) {
                                    println(response.toString())
                                    if(response.code() == 200 ){
                                        if(response.body()?.detail != null){
                                            println("NOT Upgraded")
                                        }else{
                                            println("UPGRADED")
                                            User.token = ""
                                        }
                                    }else{

                                    }
                                }
                                override fun onFailure(call: Call<createWalletResponse>, t: Throwable) {

                                }
                            })
                            // Toast.makeText(applicationContext,"User has been created",Toast.LENGTH_LONG).show()
                            Log.d("success:", "" + response.body()?.user?.username)
                            startActivity(
                                Intent(
                                    this@SignUpPageTraderActivity,
                                    LoginPageActivity::class.java
                                )
                            )

                        }
                    } else if (response.code() == 400) {
                        Log.e("Error code 400", response.toString())
                        Toast.makeText(applicationContext, call.toString(), Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG)
                            .show()
                        Log.d("error message:", response.message())
                    }
                }

                override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            }
            )

    }

    fun goToMainBasicUserActivity(view: View) {


        RetrofitClient.instance.createBasicUser(userInfo)
            .enqueue(object : Callback<BasicRegisterResponse> {
                override fun onResponse(
                    call: Call<BasicRegisterResponse>,
                    response: Response<BasicRegisterResponse>
                ) {
                    println(response.toString())
                    if (response.code() == 200) {
                        if (response.body()?.username != null) {

                        } else {
                            Log.d("success:", "" + response.body()?.user?.username)
                            startActivity(
                                Intent(
                                    this@SignUpPageTraderActivity,
                                    LoginPageActivity::class.java
                                )
                            )
                        }
                    } else if (response.code() == 400) {
                        Log.e("Error code 400", response.toString())
                        Log.e("The userInfo", userInfo.toString())
                    } else {
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG)
                            .show()
                        Log.d("error message:", response.message())
                    }
                }

                override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                    Log.d("failed message:", call.request().body().toString())
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            }
            )
    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainPageActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    fun goToLoginFromRegister(view: View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }

}
