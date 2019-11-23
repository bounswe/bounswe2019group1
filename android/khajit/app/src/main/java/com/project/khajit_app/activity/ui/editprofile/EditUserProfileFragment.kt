package com.project.khajit_app.activity.ui.editprofile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ContentView

import com.project.khajit_app.R
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.activity.LoginPageActivity
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.profile.UserProfileViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import kotlinx.android.synthetic.main.edit_user_profile_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditUserProfileFragment : Fragment() {



    private lateinit var viewModel: EditUserProfileViewModel
    private lateinit var first_name: EditText
    private lateinit var last_name: EditText
    private lateinit var title: EditText
    private lateinit var location: EditText
    private lateinit var phone_number: EditText
    private lateinit var bio :EditText
    private lateinit var iban :EditText

    private lateinit var old_pw: EditText
    private lateinit var new_pw :EditText
    private lateinit var re_new_pw:EditText

    private lateinit var button_upgrade_downgrade: Button
    private lateinit var privacy_change: Button
    private lateinit var personal_change: Button
    private lateinit var change_password: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this).get(EditUserProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.edit_user_profile_fragment, container, false)


        val bio_tex = root.findViewById(R.id.text_bio_edit) as TextView
        bio_tex.movementMethod = ScrollingMovementMethod()

        first_name = root.findViewById(R.id.input_first_name) as EditText
        last_name = root.findViewById(R.id.input_last_name)as EditText
        title  = root.findViewById(R.id.input_title)as EditText
        bio = root.findViewById(R.id.text_bio_edit)as EditText
        location = root.findViewById(R.id.input_location)as EditText
        phone_number = root.findViewById(R.id.phone_number)as EditText
        iban = root.findViewById(R.id.iban_number)as EditText

        old_pw = root.findViewById(R.id.input_old_password)as EditText
        new_pw = root.findViewById(R.id.input_new_password)as EditText
        re_new_pw = root.findViewById(R.id.input_re_new_password) as EditText

        button_upgrade_downgrade = root.findViewById(R.id.button_upgrade_downgrade) as Button
        personal_change = root.findViewById(R.id.button_apply_change_personal) as Button
        privacy_change = root.findViewById(R.id.button_change_privacy) as Button
        change_password = root.findViewById(R.id.button_apply_change_password) as Button


        first_name.setText(User.first_name)
        last_name.setText(User.last_name)
        title.setText(User.title)
        bio.setText(User.bio)
        location.setText(User.location)

        if(User.phone_number.toString() == "0")
            phone_number.setText("")
        else
            phone_number.setText(User.phone_number.toString())

        if(User.iban_number.toString() == "0") {
            iban.setText("")
            button_upgrade_downgrade.setText("Upgrade To Trader")
        } else {
            iban.setText(User.iban_number.toString())
            button_upgrade_downgrade.setText("Downgrade To Basic")
        }


        personal_change.setOnClickListener { root ->
            changePersonalInfo(root)
        }

        button_upgrade_downgrade.setOnClickListener { root ->
            upgrade_downgrade(root)
        }

        change_password.setOnClickListener { root ->
            changePasswordInfo(root)
        }


        privacy_change.setOnClickListener { root ->
            changePrivacyMode(root)
        }

        return root
    }

    fun upgrade_downgrade(root: View?) {
        var trader = User.type!!
        var iban_text = "0"
        if(!trader) {
            iban_text = iban.text.toString()
        }
        if(iban.text.length != 16 && !trader) {
            iban.error = "IBAN should be in the legnth of 16"
            iban.requestFocus()
            return
        }

        val userInfo = UpgradeDowngrade(iban_text.toLong())
        RetrofitClient.instance.changeIban(userInfo).enqueue(object :
            Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT CHANGED")
                    }else{
                        println("CHANGE IBAN")

                    }
                }else{

                }
            }
            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {

            }
        })

        if(!trader) { // If basic --> trader

            RetrofitClient.instance.upgradeUser().enqueue(object :
                Callback<GenericUserModel> {
                override fun onResponse(
                    call: Call<GenericUserModel>,
                    response: Response<GenericUserModel>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                            println("NOT Upgraded")
                        }else{
                            println("UPGRADED")
                            val intent = Intent (getActivity(), LoginPageActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

                }
            })

        } else {    // If trader --> basic

            RetrofitClient.instance.downgradeUser().enqueue(object :
                Callback<GenericUserModel> {
                override fun onResponse(
                    call: Call<GenericUserModel>,
                    response: Response<GenericUserModel>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                            println("NOT Downgraded")
                        }else{
                            println("DOWNGRADED")
                            val intent = Intent (getActivity(), LoginPageActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

                }
            })

        }
    }

    fun changePrivacyMode(root: View?) {
        val builder = AlertDialog.Builder(this.context)
        var priv_change = "Do you want to set your privacy mode as public (People can see your profile details without following you)"
        if(User.is_public == true){
            priv_change = "Do you want to set your privacy mode as private (People can't see your profile details without following you)"
        }
        builder.setTitle("Privacy Mode")
        builder.setMessage(priv_change)
        builder.setCancelable(false)
        //builder.setIcon(R.drawable.)
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            // Yes butonuna tıklayınca olacaklar
            val public= User.is_public
            val userInfo = ChangePrivacy(!(User.is_public)!!)
            RetrofitClient.instance.changePrivacy(userInfo).enqueue(object :
                Callback<UpdateUserResponse> {
                override fun onResponse(
                    call: Call<UpdateUserResponse>,
                    response: Response<UpdateUserResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                            println("NOT CHANGED")
                        }else{
                            println("CHANGED")
                            User.is_public = !public!!
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {

                }
            })
        }

        builder.setNegativeButton("No") {dialogInterface: DialogInterface, i: Int ->
            // No butonuna tıklayınca olacaklar --> Nothing
             }
        builder.show()

    }

    companion object {
        fun newInstance(): EditUserProfileFragment {
            val fragmentEditUser = EditUserProfileFragment()
            val args = Bundle()
            fragmentEditUser.arguments = args
            return fragmentEditUser
        }

    }


    fun changePersonalInfo(view: View) {

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
        if(phone_number.text.length != 12) {
            title.error = "Length of the phone number should be 12 (90532...)"
            title.requestFocus()
            return
        }

        val userInfo = UpdateUser(first_name.text.toString(), last_name.text.toString(), title.text.toString(), bio.text.toString(), phone_number.text.toString().toLong())
        RetrofitClient.instance.updateUser(userInfo).enqueue(object :
            Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT CHANGED")
                    }else{
                        println("CHANGED")
                        updateAfterRequest(view)
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {

            }
        })

    }

    fun changePasswordInfo(view: View) {

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

        val userInfo = PasswordChange(old_pw.text.toString(), new_pw.text.toString())
        RetrofitClient.instance.changePassword(userInfo).enqueue(object :
            Callback<GenericUserModel> {
            override fun onResponse(
                call: Call<GenericUserModel>,
                response: Response<GenericUserModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT CHANGED")
                        old_pw.error = "Current password does not match"
                        old_pw.requestFocus()
                    }else{
                        println("CHANGED")
                        val intent = Intent (getActivity(), LoginPageActivity::class.java)
                        getActivity()?.startActivity(intent)
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

            }
        })

    }

    // This will be used after all requests, THAT CHANGE ANY VARIABLE IN GLOBAL USER, to set Global User Class
    fun updateAfterRequest(view: View) {
        RetrofitClient.instance.getInfo(User.id.toString()).enqueue(object :
            Callback<GenericUserModel> {
            override fun onResponse(
                call: Call<GenericUserModel>,
                response: Response<GenericUserModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT CHANGED")
                    }else{
                        println("CHANGED")
                        User.username = response.body()?.username
                        User.email = response.body()?.email
                        User.first_name = response.body()?.first_name
                        User.last_name = response.body()?.last_name
                        User.location = response.body()?.location
                        User.phone_number = response.body()?.phone_number
                        User.iban_number = response.body()?.iban_number
                        User.is_public = response.body()?.is_public
                        User.bio = response.body()?.biography
                        User.title = response.body()?.title
                        User.type = response.body()?.groups?.get(0).equals("trader")
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

            }
        })
    }


}


