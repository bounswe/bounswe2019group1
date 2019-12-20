package com.project.khajit_app.activity.ui.other_profile

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import com.mikhaellopez.circularimageview.CircularImageView

import com.project.khajit_app.R
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.activity.OtherListViewAdapter
import com.project.khajit_app.activity.ui.article.ListArticleFragment
import com.project.khajit_app.activity.ui.followlist.FollowListFragment
import com.project.khajit_app.activity.ui.notificationdetails.notificationDetailFragment
import com.project.khajit_app.activity.ui.otherportfolio.OtherPortfolioFragment
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import com.squareup.picasso.Picasso
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherUserProfile : Fragment(), fragmentOperationsInterface{
    var containerId : ViewGroup? = null

    private lateinit var viewModel: OtherUserProfileViewModel
    private lateinit var other_nameBox: TextView
    private lateinit var other_titleBox: TextView
    private lateinit var other_aboutBox: TextView
    private lateinit var other_followingBox: TextView
    private lateinit var other_followerBox: TextView
    private lateinit var other_traderImage: ImageView

    private lateinit var other_followerButton: Button
    private lateinit var other_followingButton: Button
    private lateinit var follow_user: Button
    private lateinit var private_part_layout: ConstraintLayout
    private lateinit var public_private_ind: TextView
    private lateinit var other_user_article_button :Button
    private lateinit var other_portfolioButton: Button

    private var public = false
    private var isFollowing = false

    private lateinit var other_id: String
    private lateinit var other_name: String
    private lateinit var profile_pic: CircularImageView
    var profile_pic_url: String? = ""

    /*override fun onClick(v: View?) {
        if isFollowing
        // Add stack fragment
        val parentActivityManager: FragmentManager =
            activity?.supportFragmentManager as FragmentManager
        val fragment = ListArticleFragment.Companion.newInstance(0, 1, 0, 0, -1)

        fragmentTransaction(
            parentActivityManager,
            fragment,
            containerId!!.id,
            true,
            true,
            false
        )
    }*/

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
            ViewModelProviders.of(this).get(OtherUserProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.other_user_profile_fragment, container, false)
        containerId = container

        other_id = arguments?.getInt("id").toString()

        other_nameBox = root.findViewById(R.id.other_user_real_name) as TextView
        other_titleBox = root.findViewById(R.id.other_user_title) as TextView
        other_aboutBox = root.findViewById(R.id.other_text_bio) as TextView
        other_followingBox = root.findViewById(R.id.other_following_number) as TextView
        other_followerBox = root.findViewById(R.id.other_follower_number) as TextView
        other_traderImage = root.findViewById(R.id.other_trader_image) as ImageView
        other_followerButton = root.findViewById(R.id.other_follower_button) as Button
        other_followingButton = root.findViewById(R.id.other_following_button) as Button
        follow_user = root.findViewById(R.id.other_follow_button) as Button
        private_part_layout = root.findViewById(R.id.private_part) as ConstraintLayout
        public_private_ind = root.findViewById(R.id.other_public_private_text) as TextView
        other_user_article_button = root.findViewById(R.id.other_button_article_page) as Button
        other_portfolioButton = root.findViewById(R.id.other_button_portfolio_page) as Button
        profile_pic = root.findViewById(R.id.other_profile_pic) as CircularImageView

        // This will be used for further methods in order to set prediction rates
        var lview =  root.findViewById(R.id.other_list_prediction_name) as ListView
        var ladapter = OtherListViewAdapter(this, equipments, rates)
        lview.adapter = ladapter

        //other_user_article_button.setOnClickListener(this)
        RetrofitClient.instance.isFollowing(other_id).enqueue(object :
            Callback<isFollowingResponseModel> {
            override fun onResponse(
                call: Call<isFollowingResponseModel>,
                response: Response<isFollowingResponseModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("PROBLEM")
                    }else{
                        if(response.body()?.result == "Found") {
                            isFollowing = true
                            follow_user.text = "UNFOLLOW"
                            follow_user.setBackgroundColor(Color.parseColor("#AAB80707"))
                            other_followerButton.isEnabled = true
                            other_followerButton.isClickable = true
                            other_followingButton.isEnabled = true
                            other_followingButton.isClickable = true
                            private_part_layout.visibility = View.VISIBLE
                        } else {
                            isFollowing = false
                            follow_user.text = "FOLLOW"
                            follow_user.setBackgroundColor(Color.parseColor("#AA4AE608"))
                        }
                    }
                }else{
                }
            }
            override fun onFailure(call: Call<isFollowingResponseModel>, t: Throwable) {

            }
        })

        RetrofitClient.instance.getInfo(other_id).enqueue(object :
            Callback<GenericUserModel> {
            override fun onResponse(
                call: Call<GenericUserModel>,
                response: Response<GenericUserModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("PROBLEM")
                    }else{
                        other_nameBox.text = response.body()?.first_name + " " + response.body()?.last_name
                        other_name = other_nameBox.text.toString()
                        other_titleBox.text = response.body()?.title
                        other_aboutBox.text = response.body()?.biography
                        public = response.body()?.is_public!!
                        var isTrader = response.body()?.groups?.get(0).equals("trader")

                        profile_pic_url = response.body()?.photo
                        if(profile_pic_url != null) {
                            Picasso.get().load(profile_pic_url).into(profile_pic)
                        }

                        if(isTrader == true) {
                            other_traderImage.alpha = 1F
                        }

                        if(public == true) {
                            private_part_layout.visibility = View.VISIBLE
                        }
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GenericUserModel>, t: Throwable) {

            }
        })

        RetrofitClient.instance.followerListID(other_id).enqueue(object :
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
                        var count = response.body()?.list?.count()!!.toString()
                        other_followerBox.text = count
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GeneralFollowModel2>, t: Throwable) {

            }
        })

        RetrofitClient.instance.followingListID(other_id).enqueue(object :
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
                        var count = response.body()?.list?.count()!!.toString()
                        other_followingBox.text = count
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<GeneralFollowModel>, t: Throwable) {

            }
        })


        other_followerButton.setOnClickListener { root ->
            followList(root, "follower", other_id.toInt())
        }

        other_followingButton.setOnClickListener { root ->
            followList(root, "following", other_id.toInt())
        }

        follow_user.setOnClickListener { root ->
            follow_unfollow_user(root, other_id.toInt())
        }

        other_portfolioButton.setOnClickListener { root ->
            myPortfolio(root)
        }

        return root
    }

    fun myPortfolio(view: View) {
        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            OtherPortfolioFragment.newInstance(other_name, other_id),
            (containerId!!.id),
            true,
            true,
            false
        )
    }

    fun follow_unfollow_user(view: View, other_id : Int) {
        if(isFollowing) {
            val follow_type = FollowIDModel(other_id)
            RetrofitClient.instance.unfollowUser(follow_type).enqueue(object :
                Callback<FollowIDModelResponse> {
                override fun onResponse(
                    call: Call<FollowIDModelResponse>,
                    response: Response<FollowIDModelResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                        }else{
                            isFollowing = false
                            follow_user.text = "FOLLOW"
                            follow_user.setBackgroundColor(Color.parseColor("#AA4AE608"))
                            reloadFragment(other_id)
                        }
                    }else{
                    }
                }
                override fun onFailure(call: Call<FollowIDModelResponse>, t: Throwable) {
                }
            })
        } else {
            val follow_type = FollowIDModel(other_id)
            RetrofitClient.instance.followUser(follow_type).enqueue(object :
                Callback<FollowIDModelResponse> {
                override fun onResponse(
                    call: Call<FollowIDModelResponse>,
                    response: Response<FollowIDModelResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        if(response.body()?.detail != null){
                        }else{
                            isFollowing = true
                            follow_user.text = "UNFOLLOW"
                            follow_user.setBackgroundColor(Color.parseColor("#AAB80707"))
                            reloadFragment(other_id)
                        }
                    }else{

                    }
                }
                override fun onFailure(call: Call<FollowIDModelResponse>, t: Throwable) {

                }
            })
        }
    }

    fun followList(view: View, request: String, other_id: Int) {
        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager


        fragmentTransaction(
            parentActivityManager,
            FollowListFragment.newInstance(request, other_id),
            (containerId!!.id),
            true,
            true,
            false
        )
    }

    fun reloadFragment(other_id: Int) {
        var parentActivityManager: FragmentManager = activity?.supportFragmentManager as FragmentManager
        removeFragment(parentActivityManager)

        fragmentTransaction(
            parentActivityManager,
            OtherUserProfile.newInstance(other_id),
            (containerId!!.id),
            true,
            true,
            false
        )
    }

    companion object {
        fun newInstance(id: Int): OtherUserProfile {
            val fragmentUser = OtherUserProfile()
            val args = Bundle()
            args.putSerializable("id",id)
            fragmentUser.arguments = args
            return fragmentUser
        }

    }
}
