package com.project.khajit_app.activity.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.project.khajit_app.R

class HomeFragmentGuest : Fragment() {


    private lateinit var HomeGuestViewModel: HomeFragmentGuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        HomeGuestViewModel =
            ViewModelProviders.of(this).get(HomeFragmentGuestViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home_guest, container, false)
        val textView: TextView = root.findViewById(R.id.text_home_guest) as TextView
        HomeGuestViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
    companion object {
        fun newInstance(): HomeFragmentGuest {
            val fragmentGuestHome = HomeFragmentGuest()
            val args = Bundle()
            fragmentGuestHome.arguments = args
            return fragmentGuestHome
        }

    }
}
