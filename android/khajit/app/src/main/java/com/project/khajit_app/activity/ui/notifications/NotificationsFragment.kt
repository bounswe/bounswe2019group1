package com.project.khajit_app.activity.ui.notifications

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.khajit_app.R
import com.project.khajit_app.activity.HomeFeedPageActivity


import com.project.khajit_app.activity.ui.notificationdetails.notificationDetailFragment
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.GeneralNotificationModel
import com.project.khajit_app.data.models.ListNotificationsResponse
import com.project.khajit_app.data.models.UserAllInfo
import com.project.khajit_app.databinding.NotificationItemModelBinding
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotificationsFragment : Fragment(),View.OnClickListener, fragmentOperationsInterface {

    private var notifications = ArrayList<GeneralNotificationModel>()

    private var containerId : ViewGroup? = null

    private lateinit var recyclerView : RecyclerView
    private var isGuest : Int = 0
    private var isLoggedInUser : Int = 0
    private var isFeedPage: Int = 0
    private var isFollowing : Int = 0
    private var userId : Int = 0


    override fun onClick(v: View?) {

        // Add stack fragment
        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            notificationDetailFragment.newInstance("bak bakim"),
            (containerId!!.id),
            true,
            true,
            false
        )
      /*println("iki")
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
        val activity = activity as Context
        containerId = container
        recyclerView = notificationView.findViewById(R.id.notification_fragment_recyclerview) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 1)
        recyclerView.adapter = NotificationsAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,notifications,activity)

        return notificationView
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        isGuest = arguments!!.getInt(ISGUEST)
        isLoggedInUser = arguments!!.getInt(ISLOGGEDINUSER)
        isFeedPage = arguments!!.getInt(ISFEEDPAGE)
        isFollowing = arguments!!.getInt(ISFOLLOWING)
        userId = arguments!!.getInt(USERID)

        println(isGuest)
        println(isLoggedInUser)
        println(isFeedPage)
        println(isFollowing)
        println(userId)
        //if the guest home feed page is requesting
        if (isGuest == 1 && isFeedPage == 1 && isLoggedInUser == 0 && isFollowing == 0 ){
            println("girdi 1")
        }
        //if the currently logged in user is requesting
        else if(isLoggedInUser == 1){
            println("girdi 2")
            RetrofitClient.instance.getNotifications().enqueue(object :
                Callback<ListNotificationsResponse> {
                override fun onResponse(
                    call: Call<ListNotificationsResponse>,
                    response: Response<ListNotificationsResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        var count = response.body()?.count
                        notifications.clear()

                        var results = response.body()?.results as List<GeneralNotificationModel?>


                        for (notification in results) {
                            notifications.add(notification!!)
                        }

                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = NotificationsAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,notifications,context)

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

        } else {
            print("Something is wrong")
        }
    }


    companion object {

        private const val ISGUEST = "isGuest"
        private const val ISLOGGEDINUSER = "isLoggedInUser"
        private const val ISFEEDPAGE = "isFeedPage"
        private const val ISFOLLOWING = "isFollowing"
        private const val USERID = "userId"

        fun newInstance(isGuest : Int, isLoggedInUser : Int, isFeedPage: Int, isFollowing : Int, userId: Int): NotificationsFragment {
            val fragmentNotification = NotificationsFragment()
            val args = Bundle()
            args.putInt(ISGUEST,isGuest)
            args.putInt(ISLOGGEDINUSER,isLoggedInUser)
            args.putInt(ISFEEDPAGE,isFeedPage)
            args.putInt(ISFOLLOWING,isFollowing)
            args.putInt(USERID,userId)
            fragmentNotification.arguments = args
            return fragmentNotification
        }

    }


    internal inner class NotificationsAdapter(
        val isGuest : Int ,
        val isLoggedInUser : Int ,
        val isFeedPage: Int ,
        val isFollowing : Int ,
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
                        NotificationsFragment.newInstance(
                            isGuest,
                            isLoggedInUser,
                            isFeedPage,
                            isFollowing,
                            userId
                        ),
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