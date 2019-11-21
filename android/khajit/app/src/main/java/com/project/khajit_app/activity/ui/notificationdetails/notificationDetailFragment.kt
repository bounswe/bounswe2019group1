package com.project.khajit_app.activity.ui.notificationdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.khajit_app.R

class notificationDetailFragment : Fragment() {


    private lateinit var viewModel: NotificationDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(NotificationDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)


        return root
    }
    companion object {
        fun newInstance(): notificationDetailFragment {
            val fragmentGuestHome = notificationDetailFragment()
            val args = Bundle()
            fragmentGuestHome.arguments = args
            return fragmentGuestHome
        }

    }



}
