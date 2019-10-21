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
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.TraderUser
import com.project.khajit_app.data.models.userToBeLogin
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPageActivity : AppCompatActivity() {


    private lateinit var email_input : EditText
    private lateinit var password_input : EditText
    private lateinit var normal_login_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException){}

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

    }

    override fun onStart() {
        super.onStart()
        // Check if a user was already signed in

        /*
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            // User is already signed in, start HomePageActivity
            //startActivity(Intent(this, HomePageActivity::class.java))

            // Commented ^this out because I don't want it to auto-login during development
        }
        */

    }

    fun goToRegister(view:View) {
        startActivity(Intent(this, SignUpPageActivity::class.java))
    }
    fun loginAccount(view: View) {

       // startActivity(Intent(this, SignUpPageActivity::class.java))

        btn_login.setOnClickListener {
            var email_information = email_input.text.toString().trim()
            var password_information = password_input.text.toString().trim() //int olsa daha iyi gibi

            if (email_information == null) {
                email_input.error = "Email is required."
                email_input.requestFocus()
                return@setOnClickListener
            }
            if (password_information.isEmpty()) {
                password_input.error = "Password is required."
                password_input.requestFocus()
                return@setOnClickListener
            }


        }
    }


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