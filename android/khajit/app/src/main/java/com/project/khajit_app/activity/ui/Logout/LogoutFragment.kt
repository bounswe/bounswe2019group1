package com.project.khajit_app.activity.ui.equipment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.HomeFeedPageActivity
import com.project.khajit_app.activity.MainPageActivity
import com.project.khajit_app.global.User

class LogoutFragment : Fragment() {

    private lateinit var logoutViewModel: LogoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        logoutViewModel =
            ViewModelProviders.of(this).get(LogoutViewModel::class.java)
        val root = inflater.inflate(R.layout.activity_logout, container, false)

        var intent = Intent(getActivity(), MainPageActivity::class.java)
        startActivity(intent)

        User.token = ""
        User.id = 0
        User.username = ""
        User.email = ""
        User.first_name = ""
        User.last_name = ""
        User.type = ""
        User.title = ""
        User.bio = ""
        User.location = ""
        User.phone_number = 0
        User.iban_number = 0
        User.citizenship_number = ""
        User.last_changed_password_date = ""
        User.whereIamAsId = 0

        return root
    }
}