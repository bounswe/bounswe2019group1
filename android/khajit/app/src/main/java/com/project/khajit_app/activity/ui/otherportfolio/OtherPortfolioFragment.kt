package com.project.khajit_app.activity.ui.otherportfolio

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import com.project.khajit_app.activity.FollowListViewAdapter
import com.project.khajit_app.activity.OtherPortfolioListAdapter
import com.project.khajit_app.activity.PortfolioListAdapter
import com.project.khajit_app.activity.ui.other_profile.OtherUserProfile
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtherPortfolioFragment : Fragment(), fragmentOperationsInterface {
    var containerId : ViewGroup? = null

    private lateinit var myportfolioModel: OtherPortfolioModel

    private var list_titles = arrayListOf<String>()
    private var list_ids = arrayListOf<Int>()

    private lateinit var ladapter: OtherPortfolioListAdapter
    private lateinit var lview: ListView

    private lateinit var other_port_back: Button
    private lateinit var button_other_port: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        myportfolioModel =
            ViewModelProviders.of(this).get(OtherPortfolioModel::class.java)

        val root = inflater.inflate(R.layout.activity_otherportfolio, container, false)
        containerId = container

        button_other_port = root.findViewById(R.id.button_other_portfolio) as Button

        var other_id = arguments?.getString("id").toString()
        var other_name = arguments?.getString("name").toString()

        button_other_port.text = other_name + "' Portfolios"

        RetrofitClient.instance.listPortfolio(other_id).enqueue(object :
            Callback<ListPortfolioResponse> {
            override fun onResponse(
                call: Call<ListPortfolioResponse>,
                response: Response<ListPortfolioResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT CHANGED")
                    }else{
                        println("CHANGED")
                        list_titles.clear()
                        list_ids.clear()

                        var count = 0
                        count = response.body()?.results?.count()!!

                        for (a in 1..count!!) {
                            list_titles.add(response.body()?.results!!.get(a-1).name!!)
                            list_ids.add(response.body()?.results!!.get(a-1).id!!)
                        }

                        lview =  root.findViewById(R.id.list_other_portfolio) as ListView
                        ladapter = OtherPortfolioListAdapter(this@OtherPortfolioFragment, list_titles)
                        lview.adapter = ladapter

                    }
                }else{

                }
            }
            override fun onFailure(call: Call<ListPortfolioResponse>, t: Throwable) {

            }
        })

        var listview = root.findViewById(R.id.list_other_portfolio) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            val element = ladapter.getItem(position)
            var other_user_id = list_ids[position]

            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

            fragmentTransaction(
                parentActivityManager,
                OtherOnePortfolioFragment.newInstance(list_titles[position], list_ids[position]),
                (containerId!!.id),
                true,
                true,
                false
            )

        }



        other_port_back = root.findViewById(R.id.otherportfolio_button_back) as Button
        other_port_back.setOnClickListener { root ->
            val parentActivityManager: FragmentManager =
                activity?.supportFragmentManager as FragmentManager
            removeFragment(parentActivityManager)
        }
        return root
    }

    companion object {
        fun newInstance(name: String?, id: String?): OtherPortfolioFragment{
            val myportfolioFrag = OtherPortfolioFragment()
            val args = Bundle()
            args.putSerializable("name", name)
            args.putSerializable("id", id)
            myportfolioFrag.arguments = args
            return myportfolioFrag
        }

    }

}

