package com.project.khajit_app.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import com.project.khajit_app.data.models.TraderUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpPageTraderActivity : AppCompatActivity() {
    private lateinit var iban_input : EditText
    private lateinit var citizen_id_input : EditText
    private lateinit var trader_button : Button
    private lateinit var basic_user_register :Button
    private lateinit var userInfo:BasicUser
    private var googleUser:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page_trader)


        val bundle: Bundle?  = intent.extras

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
        citizen_id_input = findViewById(R.id.input_citizen_id)
        trader_button = findViewById(R.id.register_as_trader_button_first)
    }

    fun goToMainTraderUserActivity(view : View) {

        trader_button.setOnClickListener {
            var iban_information = iban_input.text.toString().toLongOrNull()
            var citizen_id_information =
                citizen_id_input.text.toString().trim() //int olsa daha iyi gibi

            if (iban_information == null) {
                iban_input.error = "IBAN is required."
                iban_input.requestFocus()
                return@setOnClickListener
            }
            if (citizen_id_information.isEmpty()) {
                citizen_id_input.error = "Citizen ID is required."
                citizen_id_input.requestFocus()
                return@setOnClickListener
            }

            val traderUserInfo = TraderUser(
                userInfo.username,
                userInfo.email,
                userInfo.first_name,
                userInfo.last_name,
                userInfo.password,
                "locationInfo",
                iban_information,
                citizen_id_information
            )
            RetrofitClient.instance.createTraderUser(traderUserInfo)
                .enqueue(object : Callback<BasicRegisterResponse> {
                    override fun onResponse(
                        call: Call<BasicRegisterResponse>?,
                        response: Response<BasicRegisterResponse>?
                    ) {
                        Toast.makeText(applicationContext, "oldu-trader", Toast.LENGTH_LONG).show()

                    }

                    override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                }

                )
            getLaunchIntent(this)
            startActivity(Intent(this, MainPageActivity::class.java))


        }
    }

    fun goToMainBasicUserActivity(view : View) {

        basic_user_register.setOnClickListener {
            RetrofitClient.instance.createBasicUser(userInfo)
                .enqueue(object : Callback<BasicRegisterResponse> {
                    override fun onResponse(
                        call: Call<BasicRegisterResponse>?,
                        response: Response<BasicRegisterResponse>?
                    ) {
                        Toast.makeText(applicationContext, "oldu-basic", Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<BasicRegisterResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                }

                )
            getLaunchIntent(this)
            startActivity(Intent(this, MainPageActivity::class.java))

        }




    }

    companion object {
        fun getLaunchIntent(from : Context) = Intent(from, MainPageActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

}
