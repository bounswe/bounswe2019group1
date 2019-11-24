package com.project.khajit_app.activity.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView

import com.project.khajit_app.R
import com.project.khajit_app.activity.ListViewAdapter

class UserProfile : Fragment() {


    private lateinit var viewModel: UserProfileViewModel


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

        return root
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
