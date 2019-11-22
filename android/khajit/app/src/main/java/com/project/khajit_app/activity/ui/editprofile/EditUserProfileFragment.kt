package com.project.khajit_app.activity.ui.editprofile

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.profile.UserProfileViewModel
import com.project.khajit_app.global.User

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

        first_name.setText(User.first_name)
        last_name.setText(User.last_name)
        title.setText(User.title)
        bio.setText(User.bio)
        location.setText(User.location)
        phone_number.setText(User.phone_number.toString())
        iban.setText(User.iban_number.toString())

        val personal_change = root.findViewById(R.id.button_apply_change_personal) as Button
        personal_change.setOnClickListener { root ->
            println("THAT WORKS")
           // changePersonalInfo(root)
        }

        val privacy_change = root.findViewById(R.id.button_change_privacy) as Button
        privacy_change.setOnClickListener { root ->
            changePrivacyMode(root)
        }

        return root
    }

    fun changePrivacyMode(root: View?) {
        val builder = AlertDialog.Builder(this.context)
        var priv_change = "Do you want to set your privacy mode as public (People can see your profile details without following)"
        if(User.is_public == true){
            priv_change = "Do you want to set your privacy mode as private (People can't see your profile details without following)"
        }
        builder.setTitle("Privacy Mode")
        builder.setMessage(priv_change)
        builder.setCancelable(false)
        //builder.setIcon(R.drawable.)
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            // Evet butonuna tıklayınca olacaklar
            println("EVET") }

        builder.setNegativeButton("No") {dialogInterface: DialogInterface, i: Int ->
            // Hayır butonuna tıklayınca olacaklar
            println("HAYIR") }
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

    private fun Button.setOnClickListener(editUserProfileFragment: EditUserProfileFragment) {
        Toast.makeText(this.context, "ASDASDASDASD", Toast.LENGTH_LONG)
        println("ASDASDASDASD")
    }

    fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_apply_change_personal -> {
                Toast.makeText(v.context, "HAHAHA", Toast.LENGTH_LONG)
                println("HAHAHA")
            }

            else -> {

            }
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

        // REQUEST HERE

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

        // REQUEST GOES HERE, do not forget to check old password validity

    }





}


