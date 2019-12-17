package com.project.khajit_app.activity.ui.myportfolio

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

class MyPortfolioFragment : Fragment(), fragmentOperationsInterface {
    var containerId : ViewGroup? = null

    private lateinit var myportfolioModel: MyPortfolioModel

    private var list_titles = arrayListOf<String>()
    private var list_ids = arrayListOf<Int>()

    private lateinit var ladapter: PortfolioListAdapter
    private lateinit var lview: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        myportfolioModel =
            ViewModelProviders.of(this).get(MyPortfolioModel::class.java)

        val root = inflater.inflate(R.layout.activity_myportfolio, container, false)
        containerId = container


        RetrofitClient.instance.listPortfolio(User.id.toString()).enqueue(object :
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
                        var count = 0
                        count = response.body()?.results?.count()!!

                        for (a in 1..count!!) {
                            list_titles.add(response.body()?.results!!.get(a-1).name!!)
                            list_ids.add(response.body()?.results!!.get(a-1).id!!)
                        }

                        lview =  root.findViewById(R.id.list_my_portfolio) as ListView
                        ladapter = PortfolioListAdapter(this@MyPortfolioFragment, list_titles)
                        lview.adapter = ladapter

                    }
                }else{

                }
            }
            override fun onFailure(call: Call<ListPortfolioResponse>, t: Throwable) {

            }
        })



        var button_back = root.findViewById(R.id.myportfolio_button_back) as Button
        button_back.setOnClickListener { root ->
            val parentActivityManager: FragmentManager =
                activity?.supportFragmentManager as FragmentManager
            removeFragment(parentActivityManager)
        }
        return root
    }

    companion object {
        fun newInstance(id: Int?): MyPortfolioFragment{
            val myportfolioFrag = MyPortfolioFragment()
            val args = Bundle()
            args.putSerializable("id", id)
            myportfolioFrag.arguments = args
            return myportfolioFrag
        }

    }

}


