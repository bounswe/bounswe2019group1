package com.project.khajit_app.activity.ui.followlist

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
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import com.project.khajit_app.activity.FollowListViewAdapter
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.activity.LoginPageActivity
import com.project.khajit_app.activity.UserViewAdapter
import com.project.khajit_app.activity.ui.other_profile.OtherUserProfile
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.profile.UserProfileViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import kotlinx.android.synthetic.main.edit_user_profile_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowListFragment : Fragment(), fragmentOperationsInterface {
    var containerId : ViewGroup? = null

    private lateinit var followlistModel: FollowListModel

    private var list_usernames = arrayListOf<String>()
    private var list_titles = arrayListOf<String>()
    private var list_ids = arrayListOf<Int>()

    private lateinit var ladapter: FollowListViewAdapter
    private lateinit var lview: ListView

    private lateinit var top_button: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        followlistModel =
            ViewModelProviders.of(this).get(FollowListModel::class.java)

        val root = inflater.inflate(R.layout.activity_followcommon, container, false)
        containerId = container
        top_button = root.findViewById(R.id.button_flw) as Button

        var request = ""
        request = arguments?.getString("request")!!
        var id = ""
        id = arguments?.getInt("id").toString()

        if(request == "follower") {
            top_button.text = "Follower List"
            //val userInfo = FollowIdListRequestModel(id)
            RetrofitClient.instance.followerListID(id).enqueue(object :
                Callback<GeneralFollowModel2> {
                override fun onResponse(
                    call: Call<GeneralFollowModel2>,
                    response: Response<GeneralFollowModel2>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                            println("NOT CHANGED")
                        }else{
                            println("CHANGED")
                            list_usernames.clear()
                            list_titles.clear()
                            list_ids.clear()
                            var count = 0
                            count = response.body()?.list?.count()!!

                            for (a in 1..count!!) {
                                list_usernames.add(response.body()?.list!!.get(a-1).follower.first_name + " " + response.body()?.list!!.get(a-1).follower.last_name)
                                list_titles.add(response.body()?.list!!.get(a-1).follower.title)
                                list_ids.add(response.body()?.list!!.get(a-1).follower.id)
                            }

                            lview =  root.findViewById(R.id.list_users_follow) as ListView
                            ladapter = FollowListViewAdapter(this@FollowListFragment, list_usernames, list_titles)
                            lview.adapter = ladapter

                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<GeneralFollowModel2>, t: Throwable) {

                }
            })


        }else if(request == "following") {
            top_button.text = "Following List"
            //val userInfo = FollowIdListRequestModel(id)
            RetrofitClient.instance.followingListID(id).enqueue(object :
                Callback<GeneralFollowModel> {
                override fun onResponse(
                    call: Call<GeneralFollowModel>,
                    response: Response<GeneralFollowModel>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                            println("NOT CHANGED")

                        }else{
                            println("BURADAYIM 2")
                            println("CHANGED")
                            list_usernames.clear()
                            list_titles.clear()
                            list_ids.clear()
                            var count = 0
                            count = response.body()?.list?.count()!!

                            for (a in 1..count!!) {
                                list_usernames.add(response.body()?.list!!.get(a-1).following.first_name + " " + response.body()?.list!!.get(a-1).following.last_name)
                                list_titles.add(response.body()?.list!!.get(a-1).following.title)
                                list_ids.add(response.body()?.list!!.get(a-1).following.id)
                            }

                            lview =  root.findViewById(R.id.list_users_follow) as ListView
                            ladapter = FollowListViewAdapter(this@FollowListFragment, list_usernames, list_titles)
                            lview.adapter = ladapter
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<GeneralFollowModel>, t: Throwable) {

                }
            })


        }

        var listview = root.findViewById(R.id.list_users_follow) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            val element = ladapter.getItem(position)
            var other_user_id = list_ids[position]

            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

            if(other_user_id == User.id) {
                fragmentTransaction(
                    parentActivityManager,
                    UserProfile.newInstance(),
                    (containerId!!.id),
                    true,
                    true,
                    false
                )
            } else {
                fragmentTransaction(
                    parentActivityManager,
                    OtherUserProfile.newInstance(other_user_id),
                    (containerId!!.id),
                    true,
                    true,
                    false
                )
            }
        }

        var button_back = root.findViewById(R.id.follow_button_back) as Button
        button_back.setOnClickListener { root ->
            val parentActivityManager: FragmentManager =
                activity?.supportFragmentManager as FragmentManager
            removeFragment(parentActivityManager)
        }
        return root
    }

    companion object {
        fun newInstance(request : String, id: Int?): FollowListFragment{
            val followListFrag = FollowListFragment()
            val args = Bundle()
            args.putSerializable("request",request)
            args.putSerializable("id", id)
            followListFrag.arguments = args
            return followListFrag
        }

    }

}


