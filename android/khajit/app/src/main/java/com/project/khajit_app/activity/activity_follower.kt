package com.project.khajit_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.FollowerModel
import com.project.khajit_app.global.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_follower : AppCompatActivity() {

    private var list_usernames = arrayListOf<String>()
    private var list_ids = arrayListOf<Int>()

    lateinit var ladapter: FollowerViewAdapter
    lateinit var lview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followcommon)

        val btn = findViewById<Button>(R.id.button_flw)
        btn.text = "Follower List"

        RetrofitClient.instance.followerList().enqueue(object :
            Callback<List<FollowerModel>> {
            override fun onResponse(
                call: Call<List<FollowerModel>>,
                response: Response<List<FollowerModel>>
            ) {
                println(response.toString())
                if(response.code() == 200 ){

                    list_usernames.clear()
                    list_ids.clear()
                    var size = response.body()?.size
                    for (a in 1..size!!) {
                        list_usernames.add(response.body()?.get(a-1)!!.follower.username)
                        list_ids.add(response.body()?.get(a-1)!!.follower.id)
                    }
                    // This will be used for further methods in order to set prediction rates
                    lview =  findViewById<ListView>(R.id.list_users_follow) as ListView
                    ladapter = FollowerViewAdapter(this@activity_follower, list_usernames)
                    lview.adapter = ladapter

                }else{
                    Log.d("error message:", response.message())
                }
            }
            override fun onFailure(call: Call<List<FollowerModel>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
            }
        })

        var listview = findViewById(R.id.list_users_follow) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            User.whereIamAsId = list_ids[position]
            var intent = Intent(this, HomeFeedPageActivity::class.java)
            startActivity(intent)
        }
    }

}
