package com.project.khajit_app.activity.ui.notifications

import java.time.LocalDateTime

import android.content.Context
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.khajit_app.R
import com.project.khajit_app.activity.HomeFeedPageActivity


import com.project.khajit_app.activity.ui.notificationdetails.notificationDetailFragment
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.annotationModels.*
import com.project.khajit_app.global.User
import com.project.khajit_app.data.models.*
import com.project.khajit_app.databinding.NotificationItemModelBinding
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

    private var notifications = ArrayList<GeneralNotificationModel>()

    private lateinit var recyclerView : RecyclerView
    private var isLoggedInUser : Int = 0
    private var userId : Int = 0


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

        /*
        fragmentTransaction(
            parentActivityManager,
            notificationDetailFragment.newInstance(notifications[0]),
            (containerId!!.id),
            true,
            true,
            false
        )
        */

    }

    private lateinit var notificationsViewModel: NotificationsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        notifications.clear()

        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)

        val notificationView :View = inflater.inflate(R.layout.fragment_notifications, container, false)
        val activity = activity as Context
        containerId = container
        //val detailButton = notificationView.findViewById(R.id.buttonToGoToNotificationDetail) as Button
        //detailButton.setOnClickListener(this)
        println(sendDateUAT.toString())

        recyclerView = notificationView.findViewById(R.id.notification_fragment_recyclerview) as RecyclerView
        //recyclerView.layoutManager = GridLayoutManager(activity, 1)
        //recyclerView.adapter = NotificationsAdapter(isLoggedInUser,userId,notifications,activity)

        return notificationView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        isLoggedInUser = arguments!!.getInt(ISLOGGEDINUSER)
        userId = arguments!!.getInt(USERID)

        println(isLoggedInUser)
        println(userId)

        //if the guest home feed page is requesting
        if (isLoggedInUser == 0){
            println("guest user notifications")
        }
        //if the currently logged in user is requesting
        else if(isLoggedInUser == 1){
            println("logged in user notifications")

            // request notifications from api
            RetrofitClient.instance.getNotifications().enqueue(object :
                Callback<ListNotificationsResponse> {
                override fun onResponse(
                    call: Call<ListNotificationsResponse>,
                    response: Response<ListNotificationsResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        var count = response.body()?.count
                        //notifications.clear()

                        val results = response.body()?.results as List<GeneralNotificationModel?>


                        for (notification in results) {
                           // if (notification?.is_active!!) {
                                notifications.add(notification!!)
                           // }
                        }

                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = NotificationsAdapter(isLoggedInUser,userId,notifications,context)

                    }else{
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<ListNotificationsResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
                }
            })

            /*
            // request pending followers from the api
            RetrofitClient.instance.getPendingFollowers().enqueue(object :
                Callback<PendingFollowerResponse> {
                override fun onResponse(
                    call: Call<PendingFollowerResponse>,
                    response: Response<PendingFollowerResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){

                        val pendingList = response.body()?.list as List<FollowModel3?>

                        for (follower in pendingList) {
                            val followRequestMessage = follower!!.follower.first_name + " " + follower!!.follower.last_name + " wants to follow you! "
                            val notification = GeneralNotificationModel(null, followRequestMessage, null, null, null, null)
                            notifications.add(notification)
                        }

                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = NotificationsAdapter(isLoggedInUser,userId,notifications,context)

                    }else{
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<PendingFollowerResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
                }
            })
            */


        } else {
            print("Something is wrong")
        }
    }


    companion object {

        private const val ISLOGGEDINUSER = "isLoggedInUser"
        private const val USERID = "userId"

        fun newInstance(isLoggedInUser : Int, userId: Int): NotificationsFragment {
            val fragmentNotification = NotificationsFragment()
            val args = Bundle()
            args.putInt(ISLOGGEDINUSER,isLoggedInUser)
            args.putInt(USERID,userId)
            fragmentNotification.arguments = args
            return fragmentNotification
        }

    }


    internal inner class NotificationsAdapter(
        val isLoggedInUser : Int ,
        val userId : Int,
        val notifications :ArrayList<GeneralNotificationModel>,
        val context: Context) :RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val notificationItemModelBinding =
                NotificationItemModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(
                notificationItemModelBinding.root,
                notificationItemModelBinding
            )
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val notification = notifications[position]
            viewHolder.setData(notification)
            //viewHolder.itemView.setOnClickListener { listener.onArticleSelected(article,isGuest,isLoggedInUser,isFeedPage,isFollowing,userId) }
            viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val parentActivityManager: FragmentManager =
                        activity?.supportFragmentManager as FragmentManager

                    fragmentTransaction(
                        parentActivityManager,
                        notificationDetailFragment.newInstance(notification),
                        (containerId!!.id),
                        true,
                        true,
                        false
                    )
                }
            })
        }

        override fun getItemCount() = notifications.size



    }

    internal inner class ViewHolder constructor(
        itemView: View,
        private val notificationItemModelBinding : NotificationItemModelBinding
    ) :
        RecyclerView.ViewHolder(itemView) {
            fun setData(generalNotificationModel: GeneralNotificationModel) {
                print("##### SET DATA İÇİ " + generalNotificationModel.text)
                notificationItemModelBinding.generalNotificationModel = generalNotificationModel
            }
        }

}
