package com.project.khajit_app.activity.ui.notificationdetails

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.FollowModel3
import com.project.khajit_app.data.models.GeneralNotificationModel
import com.project.khajit_app.data.models.PendingFollowerResponse
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class notificationDetailFragment : Fragment(),View.OnClickListener, fragmentOperationsInterface {

    var followId = 0

    var containerId : ViewGroup? = null
    override fun onClick(v: View?) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.notification_detail_fragment, container, false)
        containerId = container

        val text = root.findViewById(R.id.notificationDetailText) as TextView
        val approveButton = root.findViewById(R.id.buttonNotificationDetailApprove) as Button
        val rejectButton = root.findViewById(R.id.buttonNotificationDetailReject) as Button

        val notification = arguments!!.getSerializable(MODEL) as GeneralNotificationModel

        text.text = notification.text

        if (notification.text!!.contains("wants to follow")) {
            // this is a follow request
            approveButton.visibility = View.VISIBLE
            rejectButton.visibility = View.VISIBLE

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
                            println("---------------------------")
                            if (notification.text!!.contains(follower!!.follower.username)) {
                                followId = follower!!.id
                                println(followId)
                            }
                        }

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
        }

        approveButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                approveFollower()
            }
        })

        rejectButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                rejectFollower()
            }
        })

        return root
    }

    companion object {
        private val MODEL = "model"
        fun newInstance(notificationModel : GeneralNotificationModel): notificationDetailFragment {
            val notificationDetail = notificationDetailFragment()
            val args = Bundle()
            args.putSerializable(MODEL,notificationModel)
            notificationDetail.arguments = args
            return notificationDetail
        }

    }

    fun approveFollower() {
        // approve pending follower
        RetrofitClient.instance.approveFollower(followId).enqueue(object :
            Callback<FollowModel3> {
            override fun onResponse(
                call: Call<FollowModel3>,
                response: Response<FollowModel3>
            ) {
                println(response.toString())
                if(response.code() == 200 ){

                    Toast.makeText(context,"Approved", Toast.LENGTH_SHORT).show()

                }else{
                    Log.d("error message:", response.message())
                }
            }
            override fun onFailure(call: Call<FollowModel3>, t: Throwable) {
                println(t.message)
                println(t)
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun rejectFollower() {
        // reject pending follower
        RetrofitClient.instance.rejectFollower(followId).enqueue(object :
            Callback<FollowModel3> {
            override fun onResponse(
                call: Call<FollowModel3>,
                response: Response<FollowModel3>
            ) {
                println(response.toString())
                if(response.code() == 200 ){

                    Toast.makeText(context,"Rejected", Toast.LENGTH_SHORT).show()

                }else{
                    Log.d("error message:", response.message())
                }
            }
            override fun onFailure(call: Call<FollowModel3>, t: Throwable) {
                println(t.message)
                println(t)
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}
