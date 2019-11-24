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

import com.project.khajit_app.R
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.activity.LoginPageActivity
import com.project.khajit_app.activity.UserViewAdapter
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.profile.UserProfileViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import kotlinx.android.synthetic.main.edit_user_profile_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowListFragment : Fragment() {

    private lateinit var followlistModel: FollowListModel

    lateinit var ladapter: UserViewAdapter
    lateinit var lview: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        followlistModel =
            ViewModelProviders.of(this).get(FollowListModel::class.java)

        val root = inflater.inflate(R.layout.activity_followcommon, container, false)



        return root
    }

}


