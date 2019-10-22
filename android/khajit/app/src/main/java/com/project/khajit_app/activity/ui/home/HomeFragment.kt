package com.project.khajit_app.activity.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.EditingProfilePageActivity
import com.project.khajit_app.activity.HomeFeedPageActivity
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

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

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val bio_tex = root.findViewById(R.id.text_bio) as TextView
        bio_tex.movementMethod = ScrollingMovementMethod()

        // This will be used for further methods in order to set prediction rates
        var lview =  root.findViewById(R.id.list_prediction_name) as ListView
        var ladapter = ListViewAdapter(this, equipments, rates)
        lview.adapter = ladapter

        var b =  root.findViewById(R.id.button_about) as Button
        b.setOnClickListener(goEdit)

        var loader = root.findViewById(R.id.progress_loader) as ProgressBar
        loader.visibility = View.GONE

        //val userInfo = User.id?.let { UserInfoGet(it) }
        var userInfo = User.id
        RetrofitClient.instance.getInfo("user/retrieve/" + userInfo.toString()).enqueue(object : Callback<UserAllInfo>{
            override fun onResponse(
                call: Call<UserAllInfo>,
                response: Response<UserAllInfo>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    Toast.makeText(context,"2222222",Toast.LENGTH_LONG).show()
                    User.username = response.body()?.username
                    User.email = response.body()?.email
                    User.first_name = response.body()?.first_name
                    User.last_name = response.body()?.last_name
                    User.location = response.body()?.location
                    User.phone_number = response.body()?.phone_number
                    User.iban_number = response.body()?.iban_number
                    User.citizenship_number = response.body()?.citizenship_number
                    User.bio = response.body()?.biography
                    User.title = response.body()?.title
                    User.last_changed_password_date = response.body()?.last_changed_password_date

                    var user_name = root.findViewById(R.id.user_real_name) as TextView
                    var user_title = root.findViewById(R.id.user_title) as TextView
                    var user_bio = root.findViewById(R.id.text_bio) as TextView

                    user_name.text = User.first_name + User.last_name
                    user_title.text = User.title
                    user_bio.text = User.bio

                }else{
                    Toast.makeText(context,"3333333",Toast.LENGTH_LONG).show()
                    Log.d("error message:", response.message())
                }
            }
            override fun onFailure(call: Call<UserAllInfo>, t: Throwable) {
                Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                Toast.makeText(context,"4444444",Toast.LENGTH_LONG).show()
            }
        })
        /*
        Toast.makeText(this.context, "IM AT HOME", Toast.LENGTH_LONG).show()

        var listview = root.findViewById(R.id.list_prediction_name) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this.context, "text is " + " $position", Toast.LENGTH_LONG).show()
            var ite = ladapter.getItem(position) as String
        }
        */

        return root
    }

    private val goEdit = View.OnClickListener { view ->
        startActivity(Intent(activity, EditingProfilePageActivity::class.java))
    }

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentHome = HomeFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

}