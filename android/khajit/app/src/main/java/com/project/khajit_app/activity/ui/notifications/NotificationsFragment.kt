package com.project.khajit_app.activity.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.HomeFeedPageActivity


import com.project.khajit_app.activity.ui.notificationdetails.notificationDetailFragment
import interfaces.fragmentOperationsInterface


class NotificationsFragment : Fragment(),View.OnClickListener, fragmentOperationsInterface {
    var containerId : ViewGroup? = null
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
        containerId = container
        val detailButton = notificationView.findViewById(R.id.buttonToGoToNotificationDetail) as Button
        detailButton.setOnClickListener(this)

        return notificationView
    }
    companion object {
        fun newInstance(): NotificationsFragment {
            val fragmentNotification = NotificationsFragment()
            val args = Bundle()
            fragmentNotification.arguments = args
            return fragmentNotification
        }

    }


    /*fun swapFragment(view : View) {
        println("iki")
        val detailFragment=notificationDetailFragment.newInstance()
        (activity as HomeFeedPageActivity).denemeFragment(
            notificationDetailFragment.newInstance(),
            R.id.homePageContent, true, addToBackStack = true,
            addAnimation = true)



    }

     */



}