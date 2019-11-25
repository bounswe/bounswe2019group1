package com.project.khajit_app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.project.khajit_app.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.common.api.ApiException
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.LoginResponse
import com.project.khajit_app.data.models.TraderUser
import com.project.khajit_app.data.models.userToBeLogin
import com.project.khajit_app.global.User
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPageActivity : AppCompatActivity() {


    private lateinit var email_input : EditText
    private lateinit var password_input : EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}

        email_input = findViewById(R.id.input_email)
        password_input = findViewById(R.id.input_password)

        /*
        // [ [ [ Google Sign In ] ] ]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        //
        mGoogleSignInClient.signOut().addOnCompleteListener {
            fun onComplete(task : Task<Void>) {
                // Return to main screen
                startActivity(Intent(this, MainPageActivity::class.java))
            }
        }
        btn_login_google.setOnClickListener {
            // Launch Google Sign In Intent
            val signInIntent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent,
                RC_SIGN_IN
            )
        }
        // [ [ [ End of Google Sign In ] ] ]
         */

        var login_button =  findViewById<Button>(R.id.btn_login)
        login_button.setOnClickListener(loginBasic)

    }

    private val loginBasic = View.OnClickListener { view ->
        var email_information = email_input.text.toString().trim()
        var password_information = password_input.text.toString().trim() //int olsa daha iyi gibi

        if (email_information.isEmpty()) {
            email_input.error = "Email is required."
            email_input.requestFocus()
            return@OnClickListener
        }
        if (password_information.isEmpty()) {
            password_input.error = "Password is required."
            password_input.requestFocus()
            return@OnClickListener
        }

        val userInfo = userToBeLogin(email_information, password_information)
        RetrofitClient.instance.loginUser(userInfo).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.non_field_errors != null){
                        Toast.makeText(applicationContext, "interesting", Toast.LENGTH_LONG).show()
                        //Toast.makeText(applicationContext,response.body()?.username.toString(), Toast.LENGTH_LONG).show()

                    }else{
                        println(response)
                        println(call)
                        println(response.body()?.toString()?.trim())

                        Log.d("success:", "" + response.body()?.user?.username)



                        User.token = response.body()?.token
                        User.id = response.body()?.user?.id
                        User.username = response.body()?.user?.username
                        User.email = response.body()?.user?.email
                        User.first_name = response.body()?.user?.first_name
                        User.last_name = response.body()?.user?.last_name
                        User.location = response.body()?.user?.location
                        User.phone_number = response.body()?.user?.phone_number
                        User.iban_number = response.body()?.user?.iban_number
                        User.location = response.body()?.user?.location
                        User.bio = response.body()?.user?.biography
                        User.title = response.body()?.user?.title
                        User.is_public = response.body()?.user?.is_public
                        // if the user is trader type info will be true otherwise user is basic and type info will be false
                        User.type = (response.body()?.user?.groups?.get(0).equals("trader"))

                        startActivity(Intent(this@LoginPageActivity, HomeFeedPageActivity::class.java))
                    }
                }else{
                    Toast.makeText(applicationContext,"Password or Username is incorrect",Toast.LENGTH_LONG).show()
                    Log.d("error message:", response.message())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
            }

        }
        )

    }

    fun goToRegister(view:View) {
        startActivity(Intent(this, SignUpPageActivity::class.java))
    }
    fun loginAccount(view: View) {

        // startActivity(Intent(this, SignUpPageActivity::class.java))


    }

    override fun onBackPressed() {}


    fun forgetPassword(view: View) {
        // startActivity(Intent(this, SignUpPageActivity::class.java))
    }

    /*
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from mGoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
     */

    /*
    private fun handleSignInResult(completedTask : Task<GoogleSignInAccount> ) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(this, "Signed in, idToken: " + account?.idToken, Toast.LENGTH_SHORT).show()
            // TODO : send ID Token to server and validate
            // Signed in successfully, show authenticated UI.
            startActivity(Intent(this, HomeFeedPageActivity::class.java))
        } catch (e : ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Error while signing in", Toast.LENGTH_SHORT).show()
        }
    }
     */

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
        fun getLaunchIntent(from : Context) = Intent(from, LoginPageActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}