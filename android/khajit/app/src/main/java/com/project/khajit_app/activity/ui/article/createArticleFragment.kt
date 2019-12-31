package com.project.khajit_app.activity.ui.article

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.FragmentManager
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.CreateArticleModel
import com.project.khajit_app.data.models.CreateArticleResponseModel
import interfaces.fragmentOperationsInterface
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CreateArticleFragment : Fragment(), fragmentOperationsInterface {

    private lateinit var viewModel: CreateArticleViewModel
    private lateinit var titleOfArticle : EditText
    private lateinit var contentOfArticle : EditText
    private lateinit var image_view : ImageView
    private var isPublic : Boolean = true
    private var image_uri : Uri? = Uri.EMPTY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createArticleRoot = inflater.inflate(R.layout.create_article_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(CreateArticleViewModel::class.java)


        titleOfArticle = createArticleRoot.findViewById(R.id.createArticleFragmentTitle) as EditText
        contentOfArticle = createArticleRoot.findViewById(R.id.createArticleFragmentContent) as EditText
        val checkBox = createArticleRoot.findViewById(R.id.createArticleFragmentCheckBox) as CheckBox


        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isPublic = false
            }
        }


        var publish_button =  createArticleRoot.findViewById(R.id.createArticleFragmentPublishContent) as Button
        var pick_image = createArticleRoot.findViewById(R.id.create_article_pick_image) as Button
        image_view = createArticleRoot.findViewById(R.id.create_article_image_view) as ImageView
        publish_button.setOnClickListener(publishButtonListener)
        pick_image.setOnClickListener(pickImageListener)



        return createArticleRoot
    }

    private val pickImageListener = View.OnClickListener { view ->

            if (checkSelfPermission(context as Context,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
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


    private val publishButtonListener = View.OnClickListener { view ->
        var title_info = titleOfArticle.text.toString().trim()
        var content_info = contentOfArticle.text.toString().trim()
        if (title_info.isEmpty()) {
            titleOfArticle.error = "Title is required."
            titleOfArticle.requestFocus()
            return@OnClickListener
        }
        if (content_info.isEmpty()) {
            contentOfArticle.error = "Content is required."
            contentOfArticle.requestFocus()
            return@OnClickListener
        }
        var filePath = getPathFromURI(context!!,image_uri!!)
        var file = File(filePath)
        var requestBody  = RequestBody.create(MediaType.parse("image/*"),file)
        var part = MultipartBody.Part.createFormData("image", file.name, requestBody)
        var title = RequestBody.create(MediaType.parse("text/plain"), title_info);
        var content = RequestBody.create(MediaType.parse("text/plain"), content_info);




        /*val articleInfo = CreateArticleModel(title_info,content_info,isPublic,)
        println(articleInfo.title)
        println(articleInfo.content)
        println(articleInfo.is_public)*/


        RetrofitClient.instance.createArticle(title,content,isPublic,part).enqueue(object :
            Callback<CreateArticleResponseModel> {
            override fun onResponse(
                call: Call<CreateArticleResponseModel>,
                response: Response<CreateArticleResponseModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail == null){
                        Toast.makeText(activity?.baseContext, "Article Published!", Toast.LENGTH_SHORT).show()
                        println("Everything looks fine!")
                        val parentActivityManager: FragmentManager =
                            activity?.supportFragmentManager as FragmentManager
                        removeFragment(parentActivityManager)
                    }else{

                        println("Something went wrong!")
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<CreateArticleResponseModel>, t: Throwable) {

            }
        })


    }
    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
        fun newInstance(): CreateArticleFragment {
            val ArticleFragment = CreateArticleFragment()
            val args = Bundle()
            ArticleFragment.arguments = args
            return ArticleFragment
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

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_view.setImageURI(data?.data)
            Toast.makeText(context, "data imageeeee   ^^^^^^     " + data?.dataString, Toast.LENGTH_SHORT).show()
            image_uri = data?.data
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



