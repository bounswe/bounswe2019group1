package com.project.khajit_app.activity.ui.editprofile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ContentView
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.FragmentManager
import com.mikhaellopez.circularimageview.CircularImageView

import com.project.khajit_app.R
import com.project.khajit_app.activity.HelperFunctions
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.activity.LoginPageActivity
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.profile.UserProfileViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import com.squareup.picasso.Picasso
import interfaces.fragmentOperationsInterface
import kotlinx.android.synthetic.main.edit_user_profile_fragment.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class EditUserProfileFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

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

    private lateinit var profile_pic : CircularImageView
    private lateinit var change_image : Button
    private var image_uri : Uri? = Uri.EMPTY

    private lateinit var button_upgrade_downgrade: Button
    private lateinit var privacy_change: Button
    private lateinit var personal_change: Button
    private lateinit var change_password: Button

    private var helper = HelperFunctions()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this).get(EditUserProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.edit_user_profile_fragment, container, false)
        containerId = container

        val bio_tex = root.findViewById(R.id.text_bio_edit) as TextView
        bio_tex.movementMethod = ScrollingMovementMethod()

        first_name = root.findViewById(R.id.input_first_name) as EditText
        last_name = root.findViewById(R.id.input_last_name)as EditText
        title  = root.findViewById(R.id.input_title)as EditText
        bio = root.findViewById(R.id.text_bio_edit)as EditText
        location = root.findViewById(R.id.input_location)as EditText
        phone_number = root.findViewById(R.id.phone_number)as EditText
        iban = root.findViewById(R.id.iban_number)as EditText
        profile_pic = root.findViewById(R.id.profile_pic) as CircularImageView
        old_pw = root.findViewById(R.id.input_old_password)as EditText
        new_pw = root.findViewById(R.id.input_new_password)as EditText
        re_new_pw = root.findViewById(R.id.input_re_new_password) as EditText
        change_image = root.findViewById(R.id.change_image) as Button

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

        if(User.iban_number.toString().length == 16) {
            iban.setText(User.iban_number.toString())
            button_upgrade_downgrade.setText("Downgrade To Basic")
        } else {
            iban.setText("")
            button_upgrade_downgrade.setText("Upgrade To Trader")
        }

        if(User.photo != null) {
            Picasso.get().load("http://35.163.120.227:8000" + User.photo).into(profile_pic)
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

        profile_pic.setOnClickListener(pickImageListener)
        change_image.setOnClickListener(publishButtonListener)
        return root
    }

    fun upgrade_downgrade(root: View) {
        var trader = User.type!!
        var iban_text = ""
        if(!trader) {
            iban_text = iban.text.toString()
        }
        if(!helper.validIban(iban_text) && !trader) {
            iban.error = "IBAN should be in the legnth of 16"
            iban.requestFocus()
            return
        }

        val userInfo = UpgradeDowngrade(iban_text)
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
                        println("CHANGED IBAN")
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {

            }
        })

        if(!trader) { // If basic --> trader

            User.type = true

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
                            updateAfterRequest(root)

                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

                }
            })

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
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<createWalletResponse>, t: Throwable) {

                }
            })


        } else {    // If trader --> basic

            User.type = false
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
                            updateAfterRequest(root)

                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

                }
            })

            RetrofitClient.instance.deleteWallet().enqueue(object :
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
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<createWalletResponse>, t: Throwable) {

                }
            })

        }
    }

    fun changePrivacyMode(root: View) {
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
                            updateAfterRequest(root)

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
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
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
            phone_number.error = "Length of the phone number should be 12 (90532...)"
            phone_number.requestFocus()
            return
        }

        val userInfo = UpdateUser(first_name.text.toString(), last_name.text.toString(), title.text.toString(), bio.text.toString(), phone_number.text.toString())
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
                        removeDetails()
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
                        //User.type = response.body()?.groups?.get(0).equals("trader")

                        println()
                        println()
                        println(User.type.toString() + "    <<<<---- TYPE")
                        println()
                        println()
                        goBackFragment()
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

            }
        })
    }

    fun goBackFragment() {
        var parentActivityManager: FragmentManager = activity?.supportFragmentManager as FragmentManager
        removeFragment(parentActivityManager)
    }

    fun removeDetails() {
        User.token = ""
        User.id = 0
        User.username = ""
        User.email = ""
        User.first_name = ""
        User.last_name = ""
        // if the user is trader type info will be true otherwise user is basic and type info will be false
        User.type = false
        User.title = "No title"
        User.bio = "No bio"
        User.location = ""
        User.phone_number = ""
        User.iban_number = ""
        User.is_public = true
        User.whereIamAsId = 0 //it may be unnecessary to keep
    }

    private val publishButtonListener = View.OnClickListener { view ->
        var filePath = getPathFromURI(context!!,image_uri!!)
        var file = File(filePath)
        var requestBody  = RequestBody.create(MediaType.parse("image/*"),file)
        var part = MultipartBody.Part.createFormData("photo", file.name, requestBody)

        println("A")
        println("A")
        println("A")
        println(image_uri.toString())
        println(filePath.toString())
        println(file.toString())
        println(requestBody.toString())
        println(part.toString())
        println("B")
        println("B")
        println("B")
        RetrofitClient.instance.updateProfilePicture(part).enqueue(object :
            Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail == null){
                        User.photo = response.body()?.photo
                        Toast.makeText(context, "User Profile has been updated", Toast.LENGTH_LONG).show()
                    }else{
                        println("Something went wrong!")
                    }


                }else{

                }
            }
            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {

            }
        })

    }

    private val pickImageListener = View.OnClickListener { view ->

        if (checkSelfPermission(context as Context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            //permission denied
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
            //show popup to request runtime permission
            requestPermissions(permissions, PERMISSION_CODE);
        }
        else{
            //permission already granted
            pickImageFromGallery();
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            profile_pic.setImageURI(data?.data)
            image_uri = data?.data
            change_image.isEnabled = true
            change_image.isClickable = true
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    fun getPathFromURI(context: Context, uri: Uri): String? {
        val path: String = uri.path as String
        var realPath: String? = null

        val databaseUri: Uri
        val selection: String?
        val selectionArgs: Array<String>?
        if (path.contains("/document/image:")) { // files selected from "Documents"
            databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
        } else { // files selected from all other sources, especially on Samsung devices
            databaseUri = uri
            selection = null
            selectionArgs = null
        }
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_TAKEN
            ) // some example data you can query
            val cursor = context.contentResolver.query(
                databaseUri,
                projection, selection, selectionArgs, null
            )
            if (cursor!!.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(projection[0])
                realPath = cursor.getString(columnIndex)
            }
            cursor.close()
        } catch (e: Exception) {
            Toast.makeText(context,"zeze get path error " + e.message,Toast.LENGTH_SHORT).show()
        }
        return realPath
    }

}


