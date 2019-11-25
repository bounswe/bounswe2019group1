package com.project.khajit_app.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import com.project.khajit_app.global.User
import com.project.khajit_app.service.FetchAddressIntentService
import kotlinx.android.synthetic.main.activity_signup.*
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
    lateinit var trader_button : Button
    lateinit var basic_user_register :Button
    lateinit var google_user_register : Button

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lastLocation: Location? = null
    private var resultReceiver: AddressResultReceiver = AddressResultReceiver(Handler())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Access_Fine_Location permission is not granted

            // Request the permission.
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1212)

        } else {
            // Permission has  been granted
            getLocationAddress()
        }


        try {
            this.supportActionBar?.hide()
        } catch (e: NullPointerException) {
        }

        // [ [ [ Google Sign In ] ] ]
        // Configure sign-in to request the user's ID, IDToken, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mGoogleSignInClient.signOut().addOnCompleteListener {
            // Return to main screen when signed out
            //startActivity(Intent(this, MainPageActivity::class.java))
        }

        register_with_google_button.setOnClickListener {
            // Launch Google Sign In Intent
            val signInIntent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        // [ [ [ End of Google Sign In ] ] ]

    }

    fun goToSignUpPageTraderActivity(view : View) {

        val intent = Intent(this@SignUpPageActivity,SignUpPageTraderActivity::class.java)


        email_input = findViewById(R.id.input_email)
        password_input = findViewById(R.id.input_password)
        repeat_password_input = findViewById(R.id.input_repassword)
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
            val userInfo = BasicUser(
                username_information,
                email_information,
                firstname_information,
                lastname_information,
                password_information,
                true)
            intent.putExtra("userInfo", userInfo)
            intent.putExtra("googleUser",0)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

    }
    fun goToLoginFromRegister(view : View) {
        startActivity(Intent(this, LoginPageActivity::class.java))
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from mGoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

    }

    private fun handleSignInResult(completedTask : Task<GoogleSignInAccount>) {
        try {

            val account = completedTask.getResult(ApiException::class.java)
            //Toast.makeText(this, "Signed in as: " + account?.displayName, Toast.LENGTH_SHORT).show()

            if (account != null) {
                // send ID Token to server and register
                val userInfo = BasicUser(
                    account.id!!,
                    account.email!!,
                    account.givenName!!,
                    account.familyName!!,
                    "google11",
                    true
                )
                val intent = Intent(this@SignUpPageActivity, SignUpPageTraderActivity::class.java)

                intent.putExtra("userInfo", userInfo)
                intent.putExtra("googleUser", 0)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()

            }

            // TODO : Check BasicRegisterResponse to see if the backend server has registered and validated


        } catch (e : ApiException) {

            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Error while signing up with Google", Toast.LENGTH_SHORT).show()

        }
    }

    private fun startFetchAddressIntentService() {

        val intent = Intent(this, FetchAddressIntentService::class.java).apply {
            putExtra(FetchAddressIntentService.Constants.RECEIVER, resultReceiver)
            putExtra(FetchAddressIntentService.Constants.LOCATION_DATA_EXTRA, lastLocation)
        }
        startService(intent)
    }

    internal inner class AddressResultReceiver(handler: Handler) : ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            // Display the address string
            // or an error message sent from the intent service.
            val addressOutput = resultData?.getString(FetchAddressIntentService.Constants.RESULT_DATA_KEY) ?: ""

            // If an address was found.
            if (resultCode == FetchAddressIntentService.Constants.SUCCESS_RESULT) {
                // Show a toast message
                //showToast("Your address information is retrieved")
                // Fill the location text box
                setLocation(addressOutput)
            } else {
                // If no address was found, show error toast
                showToast(addressOutput)
                // Allow user to manually enter an address
                input_location.inputType = InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
            }

        }
    }

    private fun setLocation(addr : String) {
        // Set the input_location text box to show the retrieved address
        input_location.setText(addr)
    }

    private fun showToast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun getLocationAddress() {

        // Google Location Services

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            // Got last known location. In some rare situations this can be null.
            if (location == null) {

                // Allow user to manually enter an address
                Toast.makeText(this, "Please enable GPS for address retrieval", Toast.LENGTH_SHORT).show()
                input_location.inputType = InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // Access_Fine_Location permission is granted
                    // Request location updates
                    val mLocationRequest = LocationRequest()
                    fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null)
                }

            } else {
                // Start an IntentService in the background to convert location to street address
                //Toast.makeText(this, "location is not null", Toast.LENGTH_SHORT).show()
                lastLocation = location
                startFetchAddressIntentService()
            }
        }

        fusedLocationClient.lastLocation.addOnFailureListener {

            Toast.makeText(this, "Please allow access to location services for address retrieval", Toast.LENGTH_LONG).show()

        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            getLocationAddress()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getLocationAddress()
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

}

