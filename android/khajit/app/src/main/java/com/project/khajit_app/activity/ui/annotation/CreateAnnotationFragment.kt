package com.project.khajit_app.activity.ui.annotation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.annotationModels.*
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CreateAnnotationFragment : Fragment(), fragmentOperationsInterface {

    private lateinit var titleOfAnnotation : EditText
    var user = ""
    var source = ""
    var startChar = 0
    var endChar = 0

    companion object {

        val USER = "user"
        val SOURCE = "source"
        val STARTCHAR = "startChar"
        val ENDCHAR = "endChar"
        fun newInstance(user: String, source: String, startChar : Int, endChar : Int): CreateAnnotationFragment {
            val ArticleFragment = CreateAnnotationFragment()
            val args = Bundle()
            args.putString(USER,user)
            args.putString(SOURCE,source)
            args.putInt(STARTCHAR,startChar)
            args.putInt(ENDCHAR,endChar)
            ArticleFragment.arguments = args
            return ArticleFragment
        }
    }
    private lateinit var viewModel: CreateAnnotationFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createAnnotationView = inflater.inflate(R.layout.create_annotation_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(CreateAnnotationFragmentViewModel::class.java)
        titleOfAnnotation = createAnnotationView.findViewById(R.id.createArticleFragmentContent) as EditText

        user = arguments?.getString("user" ) as String
        source = arguments?.getString("source")!!
        startChar = arguments?.getInt("startChar" )!!
        endChar = arguments?.getInt("endChar")!!


        var back_button =  createAnnotationView.findViewById(R.id.createAnnotationTurnBack) as Button
        back_button.setOnClickListener(backButtonListener)

        var create_button =  createAnnotationView.findViewById(R.id.createAnnotationButton) as Button
        create_button.setOnClickListener(createButtonListener)
        return createAnnotationView

    }

    private val createButtonListener = View.OnClickListener { view ->

        var annotation = titleOfAnnotation.text.toString().trim()
        if (annotation.isEmpty()) {
            titleOfAnnotation.error = "Title is required."
            titleOfAnnotation.requestFocus()
            return@OnClickListener
        }
        val creatorModel = CreatorModel(user, "Person", User.first_name + " " + User.last_name, User.username as String)
        val refinedByModel = RefinedByModel( "TextPositionSelector", startChar, endChar)

        val selectorModel = SelectorModel(refinedByModel,"FragmentSelector","xpointer(/doc/body/section[2]/para[1])")
        val bodyModelList = listOf(BodyModel("TextualBody", "tagging",annotation))
        val targetModel = TargetModel(selectorModel,"text", " ",source,"")
        val today = Calendar.getInstance()
        val sendDateUAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(today.time)
        val newAnnotation = CreateAnnotationModel(creatorModel.id,bodyModelList,targetModel,"Annotation","commenting",sendDateUAT)

        RetrofitClient.instanceAnnotation.isCreatorExists(user).enqueue(object :
            Callback<CreatorExistsResponse> {
            override fun onResponse(
                call: Call<CreatorExistsResponse>,
                response: Response<CreatorExistsResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.message == "creator exists"){
                        RetrofitClient.instanceAnnotation.createAnnotation(newAnnotation).enqueue(object :
                            Callback<CreateAnnotationResponse> {
                            override fun onResponse(
                                call: Call<CreateAnnotationResponse>,
                                response: Response<CreateAnnotationResponse>
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
                                        val parentActivityManager: FragmentManager =
                                            activity?.supportFragmentManager as FragmentManager
                                        removeFragment(parentActivityManager)
                                    }
                                }else{

                                }
                            }
                            override fun onFailure(call: Call<CreateAnnotationResponse>, t: Throwable) {

                            }
                        })
                    }else{
                        val newAnnotation = CreateAnnotationModelNewCreator(creatorModel,bodyModelList,targetModel,"Annotation","commenting",sendDateUAT)

                        println("Something went wrong!")
                        RetrofitClient.instanceAnnotation.createAnnotationNewCreator(newAnnotation).enqueue(object :
                            Callback<CreateAnnotationResponse> {
                            override fun onResponse(
                                call: Call<CreateAnnotationResponse>,
                                response: Response<CreateAnnotationResponse>
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
                                        val parentActivityManager: FragmentManager =
                                            activity?.supportFragmentManager as FragmentManager
                                        removeFragment(parentActivityManager)
                                    }
                                }else{

                                }
                            }
                            override fun onFailure(call: Call<CreateAnnotationResponse>, t: Throwable) {

                            }
                        })
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<CreatorExistsResponse>, t: Throwable) {

            }
        })




    }

    private val backButtonListener = View.OnClickListener { view ->
        val parentActivityManager: FragmentManager =
                            activity?.supportFragmentManager as FragmentManager
                        removeFragment(parentActivityManager)


    }

}
