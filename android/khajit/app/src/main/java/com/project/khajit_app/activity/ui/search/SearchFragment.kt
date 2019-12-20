package com.project.khajit_app.activity.ui.search



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.UserViewAdapter
import com.project.khajit_app.activity.ui.followlist.FollowListFragment
import com.project.khajit_app.activity.ui.other_profile.OtherUserProfile
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.SearchRequest
import com.project.khajit_app.data.models.SearchResponse
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment : Fragment(), fragmentOperationsInterface {
    var containerId : ViewGroup? = null

    private lateinit var searchViewModel: SearchViewModel

    private var list_usernames = arrayListOf<String>()
    private var list_titles = arrayListOf<String>()
    private var list_ids = arrayListOf<Int>()

    private var list_search_types = arrayOf("User Search", "Article Search", "Event Search")

    lateinit var ladapter: UserViewAdapter
    lateinit var lview: ListView

    private lateinit var radio_group: RadioGroup
    private lateinit var radio1: RadioButton
    private lateinit var radio2: RadioButton
    private lateinit var radio3: RadioButton



    private var search_type = 0 // 0 --> User search, 1 --> Article search, 2 --> Event search

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        containerId = container
        var loader = root.findViewById(R.id.progress_loader) as ProgressBar
        loader.visibility = View.GONE

        radio_group = root.findViewById(R.id.radio_group) as RadioGroup
        radio1 = root.findViewById(R.id.radio1) as RadioButton
        radio2 = root.findViewById(R.id.radio2) as RadioButton
        radio3 = root.findViewById(R.id.radio3) as RadioButton

        radio1.isChecked = true
        radio2.isChecked = false
        radio3.isChecked = false

        radio_group.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radio_group, checkedId ->
            val radio: RadioButton = radio_group.findViewById(checkedId) as RadioButton
            if(radio.text.toString() == "User") {
                search_type = 0
            }else if(radio.text.toString() == "Article") {
                search_type = 1
            }else {
                search_type = 2
            }
        })


        /*
        var searchview = root.findViewById(R.id.search) as SearchView
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                var search_query = SearchRequest(query)
                RetrofitClient.instance.searchUsername(search_query).enqueue(object :
                    Callback<SearchResponse> {
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {
                        println(response.toString())
                        if(response.code() == 200 ){

                            list_usernames.clear()
                            list_titles.clear()
                            list_ids.clear()

                            var count = response.body()?.count

                            for (a in 1..count!!) {
                                list_usernames.add(response.body()?.results?.get(a-1)!!.first_name + " " + response.body()?.results?.get(a-1)!!.last_name)
                                list_titles.add(response.body()?.results?.get(a-1)!!.title)
                                list_ids.add(response.body()?.results?.get(a-1)!!.id)
                            }

                            // This will be used for further methods in order to set prediction rates
                            lview =  root.findViewById(R.id.list_users) as ListView
                            ladapter = UserViewAdapter(this@SearchFragment, list_usernames, list_titles)
                            lview.adapter = ladapter

                        }else{
                            Log.d("error message:", response.message())
                        }
                    }
                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        println(t.message)
                        println(t)
                        Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                    }
                })
                return false
            }

        })
         */
        var searchview = root.findViewById(R.id.search) as SearchView
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {

                if(search_type == 0) {

                    var search_query = SearchRequest(query)
                    RetrofitClient.instance.searchUsername(search_query).enqueue(object :
                        Callback<SearchResponse> {
                        override fun onResponse(
                            call: Call<SearchResponse>,
                            response: Response<SearchResponse>
                        ) {
                            println(response.toString())
                            if (response.code() == 200) {
                                list_usernames.clear()
                                list_titles.clear()
                                list_ids.clear()
                                var count = response.body()?.count

                                for (a in 1..count!!) {
                                    list_usernames.add(
                                        response.body()?.results?.get(a - 1)!!.first_name + " " + response.body()?.results?.get(
                                            a - 1
                                        )!!.last_name
                                    )
                                    list_titles.add(response.body()?.results?.get(a - 1)!!.title)
                                    list_ids.add(response.body()?.results?.get(a - 1)!!.id)
                                }

                                // This will be used for further methods in order to set prediction rates
                                lview = root.findViewById(R.id.list_users) as ListView
                                ladapter = UserViewAdapter(
                                    this@SearchFragment,
                                    list_usernames,
                                    list_titles
                                )
                                lview.adapter = ladapter

                            } else {
                                Log.d("error message:", response.message())
                            }
                        }

                        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                            println(t.message)
                            println(t)
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                }else if(search_type == 1) {

                }else{

                }
                return false
            }

        })

        var listview = root.findViewById(R.id.list_users) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            val element = ladapter.getItem(position)
            //<var ite = ladapter!!.getItem(position) as String
            //User.whereIamAsId = list_ids[position]
            var other_user_id = list_ids[position]

            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

            if(other_user_id == User.id) {
                fragmentTransaction(
                    parentActivityManager,
                    UserProfile.newInstance(),
                    (containerId!!.id),
                    true,
                    true,
                    false
                )
            } else {
                fragmentTransaction(
                    parentActivityManager,
                    OtherUserProfile.newInstance(other_user_id),
                    (containerId!!.id),
                    true,
                    true,
                    false
                )
            }
        }


        return root
    }

}