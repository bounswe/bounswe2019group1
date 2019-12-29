package com.project.khajit_app.activity.ui.search



import android.os.Bundle
import android.text.SpannableString
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
import com.project.khajit_app.activity.ui.article.displayArticleFragment
import com.project.khajit_app.activity.ui.followlist.FollowListFragment
import com.project.khajit_app.activity.ui.other_profile.OtherUserProfile
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
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

    private var articleIds = arrayListOf<Int>()
    private var titles = arrayListOf<String>()
    private var contents = arrayListOf<String>()
    private var authors = arrayListOf<UserAllInfo>()
    private var is_public_info = arrayListOf<Boolean>()
    private var created_dates = arrayListOf<String>()
    private var images = arrayListOf<String?>()


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
            list_usernames.clear()
            list_titles.clear()
            list_ids.clear()

            articleIds.clear()
            titles.clear()
            contents.clear()
            authors.clear()
            is_public_info.clear()
            created_dates.clear()
            images.clear()

            lview = root.findViewById(R.id.list_users) as ListView
            ladapter = UserViewAdapter(
                this@SearchFragment,
                list_usernames,
                list_titles
            )
            lview.adapter = ladapter
            if(radio.text.toString() == "User") {
                search_type = 0
            }else if(radio.text.toString() == "Article") {
                search_type = 1
            }else {
                search_type = 2
            }
        })
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

                                articleIds.clear()
                                titles.clear()
                                contents.clear()
                                authors.clear()
                                is_public_info.clear()
                                created_dates.clear()
                                images.clear()
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
                    RetrofitClient.instance.searchArticle(query).enqueue(object :
                        Callback<ArticleSearchResponse> {
                        override fun onResponse(
                            call: Call<ArticleSearchResponse>,
                            response: Response<ArticleSearchResponse>
                        ) {
                            println(response.toString())
                            if (response.code() == 200) {
                                list_usernames.clear()
                                list_titles.clear()
                                list_ids.clear()

                                articleIds.clear()
                                titles.clear()
                                contents.clear()
                                authors.clear()
                                is_public_info.clear()
                                created_dates.clear()
                                images.clear()
                                var count = response.body()?.results!!.count()
                                for (a in 1..count!!) {
                                    list_usernames.add(
                                        response.body()?.results?.get(a - 1)!!.title!!
                                    )
                                    var author = response.body()?.results?.get(a - 1)!!.author!!.first_name + " " + response.body()?.results?.get(a - 1)!!.author!!.last_name
                                    if (author == "") {
                                        list_titles.add("Anonymous")
                                    }else {
                                        list_titles.add(author)
                                    }

                                    list_ids.add(response.body()?.results?.get(a - 1)!!.id!!)
                                }

                                var results = response.body()?.results as List<ArticleSearchModelResponse>
                                for (article in results) {
                                    articleIds.add(article.id as Int)
                                    titles.add(article.title as String)
                                    contents.add(article.content as String)
                                    authors.add(UserAllInfo(null, article.author!!.id, article.author!!.username, article.author!!.first_name, article.author!!.last_name, "", article.author!!.location, article.author!!.phone_number, article.author!!.iban_number, article.author!!.citizenship_number, article.author!!.biography, article.author!!.title, article.author!!.last_changed_password_date))
                                    is_public_info.add(article.is_public as Boolean)
                                    created_dates.add(article.created_date as String)
                                    if(article.image == null) {
                                        images.add(null)
                                    }else {
                                        images.add("http://35.163.120.227:8000" + article.image)
                                    }

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

                        override fun onFailure(call: Call<ArticleSearchResponse>, t: Throwable) {
                            println(t.message)
                            println(t)
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                }else{
                    RetrofitClient.instance.searchEvent(query).enqueue(object :
                        Callback<ListEventResponse> {
                        override fun onResponse(
                            call: Call<ListEventResponse>,
                            response: Response<ListEventResponse>
                        ) {
                            println(response.toString())
                            if (response.code() == 200) {
                                list_usernames.clear()
                                list_titles.clear()
                                list_ids.clear()

                                articleIds.clear()
                                titles.clear()
                                contents.clear()
                                authors.clear()
                                is_public_info.clear()
                                created_dates.clear()
                                images.clear()
                                var count = response.body()?.results?.count()

                                for (a in 1..count!!) {
                                    list_usernames.add(
                                        response.body()?.results?.get(a - 1)!!.title!!
                                    )
                                    list_titles.add(response.body()?.results?.get(a - 1)!!.country!!)
                                    list_ids.add(0)
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

                        override fun onFailure(call: Call<ListEventResponse>, t: Throwable) {
                            println(t.message)
                            println(t)
                            Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                }
                return false
            }

        })

        var listview = root.findViewById(R.id.list_users) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            val element = ladapter.getItem(position)
            var other_user_id = list_ids[position]



            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

            if(search_type == 0) {
                if (other_user_id == User.id) {
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
            } else if(search_type == 1) {
                val article = GeneralArticleModel(articleIds[position], titles[position],
                    contents[position], authors[position],is_public_info[position],created_dates[position],images[position])

                fragmentTransaction(
                    parentActivityManager,
                    displayArticleFragment.newInstance(article,0,1,0,1,User.id!!),
                    (containerId!!.id),
                    true,
                    true,
                    false)
            } else {

            }
        }


        return root
    }

}