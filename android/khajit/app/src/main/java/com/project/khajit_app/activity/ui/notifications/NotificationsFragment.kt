package com.project.khajit_app.activity.ui.notifications
import java.time.LocalDateTime
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.github.kittinunf.fuel.android.core.Json
import com.project.khajit_app.R
import com.project.khajit_app.activity.HomeFeedPageActivity


import com.project.khajit_app.activity.ui.notificationdetails.notificationDetailFragment
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.annotationModels.*
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class NotificationsFragment : Fragment(),View.OnClickListener, fragmentOperationsInterface {
    var containerId : ViewGroup? = null
    val creatorModel = CreatorModel("http://exampleAndroid.org/" + User.id +"Deneme2", "Person", User.first_name + " " + User.last_name, User.username as String)

    val bodyModelList = listOf(BodyModel("android dsdeneme new body", "tagging","love"))
    val addBodyModel = AddBodyModel("http://khajiittraders.tk/annotation_id14",bodyModelList)
    val deleteAn = DeleteAnnotationModel("http://khajiittraders.tk/annotation_id25")

    val refinedByModel = RefinedByModel( "TextPositionSelector", 4, 8)
    val selectorModel = SelectorModel(refinedByModel,"FragmentSelector","xpointer(/doc/body/section[2]/para[1])")
    val targetModel = TargetModel(selectorModel,"text", " ","http://www.khajiittraderssdvANDORİD.tk/article/1/","")
    /*val time =  Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
    val formatedDate = formatter.format(time)*/
    val today = Calendar.getInstance()
    val sendDateUAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(today.time)
    val newAnnotation = CreateAnnotationModel(creatorModel.id,bodyModelList,targetModel,"Annotation","commenting",sendDateUAT)

    override fun onClick(v: View?) {

        RetrofitClient.instanceAnnotation.getAnnotationsBySource("http://www.khajiittraders.tk/article/10/").enqueue(object :
            Callback<GetAnnotationModelResponse> {
            override fun onResponse(
                call: Call<GetAnnotationModelResponse>,
                response: Response<GetAnnotationModelResponse>
            ) {

                println("\n #########################")
                println(response.toString())
                if(response.code() == 200){
                    if(response.body()!!.result == null)
                        Toast.makeText(context,"Response döndü 1 " + response.body()!!.result,Toast.LENGTH_LONG).show()
                    else{
                        Toast.makeText(context,"Response döndü 1 " + response.body()!!.result[0].id,Toast.LENGTH_LONG).show()
                    }
                }else{
                    println(response.message())
                }



                /* if(response.body()!!.details ==null){
                     Toast.makeText(context,"Response döndü 1 " + response.body()!!.creator,Toast.LENGTH_LONG).show()
                     println("response " + response.code() )
                 }else{
                     Toast.makeText(context,"Response döndü 1 " + response.body()!!.details,Toast.LENGTH_LONG).show()

                 }*/


            }
            override fun onFailure(call: Call<GetAnnotationModelResponse>, t: Throwable) {
                println(t.message)
                println(t)
                Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
            }
        })
        // Add stack fragment
        //val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager


        /*fragmentTransaction(
            parentActivityManager,
            notificationDetailFragment.newInstance("bak bakim"),
            (containerId!!.id),
            true,
            true,
            false
        )
      println("iki")
        val detailFragment=notificationDetailFragment.newInstance()
        (activity as HomeFeedPageActivity).denemeFragment(
            notificationDetailFragment.newInstance(),
            R.id.homePageContent, true, addToBackStack = true,
            addAnimation = false)*/


    }

    private lateinit var notificationsViewModel: NotificationsViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val notificationView :View = inflater.inflate(R.layout.fragment_notifications, container, false)
        containerId = container
        val detailButton = notificationView.findViewById(R.id.buttonToGoToNotificationDetail) as Button
        detailButton.setOnClickListener(this)
        println(sendDateUAT.toString())
        return notificationView
    }
    companion object {
        fun newInstance(): NotificationsFragment {
            val fragmentNotification = NotificationsFragment()
            val args = Bundle()
            fragmentNotification.arguments = args
            return fragmentNotification
        }

    }


    /*fun swapFragment(view : View) {
        println("iki")
        val detailFragment=notificationDetailFragment.newInstance()
        (activity as HomeFeedPageActivity).denemeFragment(
            notificationDetailFragment.newInstance(),
            R.id.homePageContent, true, addToBackStack = true,
            addAnimation = true)



    }

     */



}