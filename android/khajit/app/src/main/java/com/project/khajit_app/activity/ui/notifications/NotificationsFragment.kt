package com.project.khajit_app.activity.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.HomeFeedPageActivity


import com.project.khajit_app.activity.ui.notificationdetails.notificationDetailFragment


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val notificationView :View = inflater.inflate(R.layout.fragment_notifications, container, false)

        val detailButton = notificationView.findViewById(R.id.buttonToGoToNotificationDetail) as Button
        /*detailButton.setOnClickListener {
            println("bir")
            swapFragment()
            println("üç")
        }

         */



        return notificationView
    }
    companion object {
        fun newInstance(): NotificationsFragment {
            val fragmentGuestHome = NotificationsFragment()
            val args = Bundle()
            fragmentGuestHome.arguments = args
            return fragmentGuestHome
        }

    }


    fun swapFragment(view : View) {
        println("iki")
        val detailFragment=notificationDetailFragment.newInstance()
        (activity as HomeFeedPageActivity).denemeFragment(
            notificationDetailFragment.newInstance(),
            R.id.homePageContent, true, addToBackStack = true,
            addAnimation = true)



    }



}