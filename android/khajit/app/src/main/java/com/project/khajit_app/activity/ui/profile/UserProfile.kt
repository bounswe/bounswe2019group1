package com.project.khajit_app.activity.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.project.khajit_app.R
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.FollowModel
import com.project.khajit_app.data.models.GeneralFollowModel
import com.project.khajit_app.global.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfile : Fragment() {


    private lateinit var viewModel: UserProfileViewModel
    private lateinit var nameBox: TextView
    private lateinit var titleBox: TextView
    private lateinit var aboutBox: TextView
    private lateinit var followingBox: TextView
    private lateinit var followerBox: TextView
    private lateinit var traderImage: ImageView

    private lateinit var followerButton: Button
    private lateinit var followingButton: Button



    var equipments = arrayOf(
        "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Max OS X", "Max OS X")
    var rates = arrayOf(
        "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.user_profile_fragment, container, false)

        val bio_tex = root.findViewById(R.id.text_bio) as TextView
        bio_tex.movementMethod = ScrollingMovementMethod()

        // This will be used for further methods in order to set prediction rates
        var lview =  root.findViewById(R.id.list_prediction_name) as ListView
        var ladapter = ListViewAdapter(this, equipments, rates)
        lview.adapter = ladapter


        var loader = root.findViewById(R.id.progress_loader) as ProgressBar
        loader.visibility = View.GONE

        nameBox = root.findViewById(R.id.user_real_name) as TextView
        titleBox = root.findViewById(R.id.user_title) as TextView
        aboutBox = root.findViewById(R.id.text_bio) as TextView
        followingBox = root.findViewById(R.id.following_number) as TextView
        followerBox = root.findViewById(R.id.follower_number) as TextView
        traderImage = root.findViewById(R.id.trader_image) as ImageView

        followerButton = root.findViewById(R.id.follower_button) as Button
        followingButton = root.findViewById(R.id.following_button) as Button

        nameBox.text = User.first_name + " " + User.last_name
        titleBox.text = User.title
        aboutBox.text = User.bio

        RetrofitClient.instance.followingList().enqueue(object :
            Callback<GeneralFollowModel> {
            override fun onResponse(
                call: Call<GeneralFollowModel>,
                response: Response<GeneralFollowModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("PROBLEM FOLLOWING LIST")
                    }else{
                        followingBox.text = response.body()?.list?.size.toString()
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GeneralFollowModel>, t: Throwable) {

            }
        })

        RetrofitClient.instance.followerList().enqueue(object :
            Callback<GeneralFollowModel> {
            override fun onResponse(
                call: Call<GeneralFollowModel>,
                response: Response<GeneralFollowModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("PROBLEM FOLLOWING LIST")
                    }else{
                        followerBox.text = response.body()?.list?.size.toString()
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GeneralFollowModel>, t: Throwable) {

            }
        })

        if(User.type == true) {
            traderImage.alpha = 1F
        }

        followerButton.setOnClickListener { root ->
            followList(root, "follower")
        }

        followingButton.setOnClickListener { root ->
            followList(root, "following")
        }

        return root
    }


    fun followList(view: View, request: String) {

    }

    companion object {
        fun newInstance(): UserProfile {
            val fragmentUser = UserProfile()
            val args = Bundle()
            fragmentUser.arguments = args
            return fragmentUser
        }

    }
}
