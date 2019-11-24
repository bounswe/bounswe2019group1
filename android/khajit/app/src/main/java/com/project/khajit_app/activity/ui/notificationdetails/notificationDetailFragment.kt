package com.project.khajit_app.activity.ui.notificationdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import interfaces.fragmentOperationsInterface

class notificationDetailFragment : Fragment(),View.OnClickListener, fragmentOperationsInterface {


    private lateinit var viewModel: NotificationDetailViewModel
    var containerId : ViewGroup? = null
    override fun onClick(v: View?) {

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(NotificationDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.notification_detail_fragment, container, false)
        containerId = container
        val detailButton1 = root.findViewById(R.id.buttonNotificationDetailTest) as Button
        val detailButton2 = root.findViewById(R.id.buttonNotificationDetailTest2) as Button
        //detailButton.setOnClickListener(this)

        detailButton1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // Remove stack fragment
                val parentActivityManager: FragmentManager =
                    activity?.supportFragmentManager as FragmentManager
                removeFragment(parentActivityManager)
            }
        })

        return root
    }
    companion object {
        private val DENEME = "model"
        fun newInstance(denemeS : String): notificationDetailFragment {
            val notificationDetail = notificationDetailFragment()
            val args = Bundle()
            args.putSerializable(DENEME,denemeS)
            notificationDetail.arguments = args
            return notificationDetail
        }

    }



}
