package com.project.khajit_app.activity.ui.settings

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.project.khajit_app.R
import com.project.khajit_app.activity.ui.equipment.SettingsViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    private lateinit var first_name: EditText
    private lateinit var last_name: EditText
    private lateinit var title: EditText
    private lateinit var bio: EditText

    private lateinit var old_pw: EditText
    private lateinit var new_pw: EditText
    private lateinit var re_new_pw: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)

        var text_biography = root.findViewById(R.id.text_bio_edit) as TextView
        text_biography.movementMethod = ScrollingMovementMethod()

        var personal_button = root.findViewById(R.id.button_apply_change_personal) as Button
        personal_button.setOnClickListener(changePersonalInfo)

        var password_button = root.findViewById(R.id.button_apply_change_password) as Button
        password_button.setOnClickListener(changePasswordInfo)

        first_name = root.findViewById(R.id.input_first_name) as EditText
        last_name = root.findViewById(R.id.input_last_name) as EditText
        title = root.findViewById(R.id.input_title) as EditText
        bio = root.findViewById(R.id.text_bio_edit) as EditText

        old_pw = root.findViewById(R.id.input_old_password) as EditText
        new_pw = root.findViewById(R.id.input_new_password) as EditText
        re_new_pw = root.findViewById(R.id.input_re_new_password) as EditText

        first_name.setText(User.first_name)
        last_name.setText(User.last_name)
        title.setText(User.title)
        bio.setText(User.bio)

        return root
    }

    private val changePersonalInfo = View.OnClickListener { view ->
        if(first_name.text.isEmpty()){
            first_name.error = "First name is required."
            first_name.requestFocus()
            return@OnClickListener
        }
        if(first_name.text.length > 20) {
            first_name.error = "Max length of first name should not exceed 20"
            first_name.requestFocus()
            return@OnClickListener
        }
        if(last_name.text.isEmpty()){
            last_name.error = "Last name is required."
            last_name.requestFocus()
            return@OnClickListener
        }
        if(last_name.text.length > 20) {
            last_name.error = "Max length of last name should not exceed 20"
            last_name.requestFocus()
            return@OnClickListener
        }
        if(title.text.length > 20) {
            title.error = "Max length of title should not exceed 20"
            title.requestFocus()
            return@OnClickListener
        }

        var text_fn = first_name.text.toString()
        var text_ln = last_name.text.toString()
        var text_title = title.text.toString()
        var text_bio = bio.text.toString()

        var update_user = UpdateUser(text_fn, text_ln, text_title, text_bio)
        RetrofitClient.instance.updateUser(update_user).enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){

                    User.first_name = text_fn
                    User.last_name = text_ln
                    User.title = text_title
                    User.bio = text_bio

                    Toast.makeText(view.context, "User password has been updated", Toast.LENGTH_LONG).show()

                }else{
                    Log.d("error message:", response.message())
                    Toast.makeText(view.context, "Password is not correct", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                Toast.makeText(view.context,"Password is not correct", Toast.LENGTH_LONG).show()
            }
        })

    }

    private val changePasswordInfo = View.OnClickListener { view ->
        if(old_pw.text.isEmpty()){
            old_pw.error = "Current password is required."
            old_pw.requestFocus()
            return@OnClickListener
        }
        if(new_pw.text.isEmpty()){
            new_pw.error = "New password is required."
            new_pw.requestFocus()
            return@OnClickListener
        }
        if(re_new_pw.text.isEmpty()){
            re_new_pw.error = "Repeating new password is required."
            re_new_pw.requestFocus()
            return@OnClickListener
        }
        if(new_pw.text.length < 6 || new_pw.text.length > 20){
            new_pw.error = "Password length should be in the range of 6-20"
            new_pw.requestFocus()
            return@OnClickListener
        }
        if(new_pw.text.toString() != re_new_pw.text.toString()) {
            re_new_pw.error = "Two passwords should match"
            re_new_pw.requestFocus()
            return@OnClickListener
        }
        if(old_pw.text.toString() == new_pw.text.toString()){
            new_pw.error = "New password should be different from previous password"
            new_pw.requestFocus()
            return@OnClickListener
        }

        // REQUEST GOES HERE, do not forget to check old password validity

        var pw_class = PasswordChange(old_pw.text.toString(), new_pw.text.toString())
        RetrofitClient.instance.changePassword(pw_class).enqueue(object : Callback<GenericUserModel> {
            override fun onResponse(
                call: Call<GenericUserModel>,
                response: Response<GenericUserModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){

                    Toast.makeText(view.context, "User profile has been updated", Toast.LENGTH_LONG).show()

                }else{
                    Log.d("error message:", response.message())
                }
            }
            override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {
                println(t.message)
                println(t)
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
            }
        })

    }
}