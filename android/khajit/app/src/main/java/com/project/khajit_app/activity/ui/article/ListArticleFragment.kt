package com.project.khajit_app.activity.ui.article

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.GeneralArticleModel
import com.project.khajit_app.data.models.PublicArticleListResponse
import com.project.khajit_app.data.models.UserAllInfo
import com.project.khajit_app.databinding.DisplayArticleRecyclerviewItemModelBinding
import interfaces.fragmentOperationsInterface
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.display_article_recyclerview_item_model.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.contracts.contract

class ListArticleFragment : Fragment() , fragmentOperationsInterface {



    private var articleIds = ArrayList<Int>()
    private var titles = ArrayList<String>()
    private var contents = ArrayList<String>()
    private var authors = ArrayList<UserAllInfo>()
    private var is_public_info = ArrayList<Boolean>()
    private var created_dates = ArrayList<String>()
    private var image_urls = ArrayList<String?>()

    private var containerId : ViewGroup? = null
    private lateinit var recyclerView : RecyclerView

    private var isGuest : Int = 0
    private var isLoggedInUser : Int = 0
    private var isFeedPage: Int = 0
    private var isFollowing : Int = 0
    private var userId : Int = 0

    companion object {
        private const val ISGUEST = "isGuest"
        private const val ISLOGGEDINUSER = "isLoggedInUser"
        private const val ISFEEDPAGE = "isFeedPage"
        private  const val ISFOLLOWING = "isFollowing"
        private  const val USERID = "userId"


        fun newInstance(isGuest : Int, isLoggedInUser : Int, isFeedPage: Int, isFollowing : Int, userId: Int): ListArticleFragment {
            val args = Bundle()
            args.putInt(ISGUEST,isGuest)
            args.putInt(ISLOGGEDINUSER,isLoggedInUser)
            args.putInt(ISFEEDPAGE,isFeedPage)
            args.putInt(ISFOLLOWING,isFollowing)
            args.putInt(USERID,userId)
            val fragment = ListArticleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: ListArticleViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        isGuest = arguments!!.getInt(ISGUEST)
        isLoggedInUser = arguments!!.getInt(ISLOGGEDINUSER)
        isFeedPage = arguments!!.getInt(ISFEEDPAGE)
        isFollowing = arguments!!.getInt(ISFOLLOWING)
        userId = arguments!!.getInt(USERID)

        println(isGuest)
        println(isLoggedInUser)
        println(isFeedPage)
        println(isFollowing)
        println(userId)
        //if the guest home feed page is requesting
        if (isGuest == 1 && isFeedPage == 1 && isLoggedInUser == 0 && isFollowing == 0 ){
            println("girdi 1")
            RetrofitClient.instance.getPublicArticles().enqueue(object :
                Callback<PublicArticleListResponse> {
                override fun onResponse(
                    call: Call<PublicArticleListResponse>,
                    response: Response<PublicArticleListResponse>
                ) {
                    println(response.toString())
                    print("response " + (response.code() == 200 ))
                    if(response.code() == 200 ){
                        print("burdayız")
                        var count = response.body()?.count
                        articleIds.clear()
                        titles.clear()
                        contents.clear()
                        authors.clear()
                        is_public_info.clear()
                        created_dates.clear()
                        image_urls.clear()

                        var results = response.body()?.results as List<GeneralArticleModel>
                        print(count)

                        for (article in results) {
                            articleIds.add(article.id as Int)
                            titles.add(article.title as String)
                            contents.add(article.content as String)
                            authors.add(article.author as UserAllInfo)
                            is_public_info.add(article.is_public as Boolean)
                            created_dates.add(article.created_date as String)
                            image_urls.add(article.image as? String)
                            print(article.title)
                        }
                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = ArticleListAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,articleIds,titles,contents,authors,is_public_info,created_dates,image_urls,context)

                    }else{
                        print("nalaka")
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<PublicArticleListResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                }
            })
        }
        //if the home feed page is requesting
        else if(isGuest == 0 && isFeedPage == 1 && isLoggedInUser == 1 && isFollowing == 0){
            println("girdi 2")
            RetrofitClient.instance.getPublicArticles().enqueue(object :
                Callback<PublicArticleListResponse> {
                override fun onResponse(
                    call: Call<PublicArticleListResponse>,
                    response: Response<PublicArticleListResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        var count = response.body()?.count
                        articleIds.clear()
                        titles.clear()
                        contents.clear()
                        authors.clear()
                        is_public_info.clear()
                        created_dates.clear()

                        var results = response.body()?.results as List<GeneralArticleModel>


                        for (article in results) {
                            articleIds.add(article.id as Int)
                            titles.add(article.title as String)
                            contents.add(article.content as String)
                            authors.add(article.author as UserAllInfo)
                            is_public_info.add(article.is_public as Boolean)
                            created_dates.add(article.created_date as String)
                        }

                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = ArticleListAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,articleIds,titles,contents,authors,is_public_info,created_dates,image_urls,context)

                    }else{
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<PublicArticleListResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                }
            })

        }
        //if the currently logged in user is requesting
        else if(isGuest == 0 && isFeedPage == 0 && isLoggedInUser == 1&& isFollowing == 0){
            println("girdi 3")
            RetrofitClient.instance.getArticlesByUserId(userId).enqueue(object :
                Callback<PublicArticleListResponse> {
                override fun onResponse(
                    call: Call<PublicArticleListResponse>,
                    response: Response<PublicArticleListResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        articleIds.clear()
                        titles.clear()
                        contents.clear()
                        authors.clear()
                        is_public_info.clear()
                        created_dates.clear()

                        var results = response.body()?.results as List<GeneralArticleModel>


                        for (article in results) {
                            articleIds.add(article.id as Int)
                            titles.add(article.title as String)
                            contents.add(article.content as String)
                            authors.add(article.author as UserAllInfo)
                            is_public_info.add(article.is_public as Boolean)
                            created_dates.add(article.created_date as String)
                        }
                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = ArticleListAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,articleIds,titles,contents,authors,is_public_info,created_dates,image_urls,context)


                    }else{
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<PublicArticleListResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                }
            })
        }
        //if the following user page requesting
        else if(isGuest == 0 && isFeedPage == 0 && isLoggedInUser == 0 && isFollowing == 1){
            println("girdi 4")
            RetrofitClient.instance.getArticlesByUserId(userId).enqueue(object :
                Callback<PublicArticleListResponse> {
                override fun onResponse(
                    call: Call<PublicArticleListResponse>,
                    response: Response<PublicArticleListResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        articleIds.clear()
                        titles.clear()
                        contents.clear()
                        authors.clear()
                        is_public_info.clear()
                        created_dates.clear()

                        var results = response.body()?.results as List<GeneralArticleModel>


                        for (article in results) {
                            articleIds.add(article.id as Int)
                            titles.add(article.title as String)
                            contents.add(article.content as String)
                            authors.add(article.author as UserAllInfo)
                            is_public_info.add(article.is_public as Boolean)
                            created_dates.add(article.created_date as String)
                        }
                        recyclerView.layoutManager = GridLayoutManager(activity, 1)
                        recyclerView.adapter = ArticleListAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,articleIds,titles,contents,authors,is_public_info,created_dates,image_urls,context)


                    }else{
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<PublicArticleListResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                }
            })
        }
        //if the not following page is requesting
        else if(isGuest == 0 && isFeedPage == 0 && isLoggedInUser == 0 && isFollowing == 0){
            RetrofitClient.instance.getArticlesByUserId(userId).enqueue(object :
                Callback<PublicArticleListResponse> {
                override fun onResponse(
                    call: Call<PublicArticleListResponse>,
                    response: Response<PublicArticleListResponse>
                ) {
                    println(response.toString())
                    if(response.code() == 200 ){
                        articleIds.clear()
                        titles.clear()
                        contents.clear()
                        authors.clear()
                        is_public_info.clear()
                        created_dates.clear()

                        var results = response.body()?.results as List<GeneralArticleModel>


                        for (article in results) {
                            if( article.is_public as Boolean){
                                articleIds.add(article.id as Int)
                                titles.add(article.title as String)
                                contents.add(article.content as String)
                                authors.add(article.author as UserAllInfo)
                                is_public_info.add(article.is_public as Boolean)
                                created_dates.add(article.created_date as String)
                            }

                        }


                    }else{
                        Log.d("error message:", response.message())
                    }
                }
                override fun onFailure(call: Call<PublicArticleListResponse>, t: Throwable) {
                    println(t.message)
                    println(t)
                    Toast.makeText(context,t.message,Toast.LENGTH_LONG).show()
                }
            })
        }else{
            print("Something is wrong")
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.list_article_fragment, container,
            false)
        val activity = activity as Context
        containerId = container
        recyclerView = view.findViewById(R.id.list_article_fragment_recyclerview) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 1)
        recyclerView.adapter = ArticleListAdapter(isGuest,isLoggedInUser,isFeedPage,isFollowing,userId,articleIds,titles,contents,authors,is_public_info,created_dates,image_urls,activity)


        return view
    }


    internal inner class ArticleListAdapter(
                        val isGuest : Int ,
                        val isLoggedInUser : Int ,
                        val isFeedPage: Int ,
                        val isFollowing : Int ,
                        val userId : Int,
                        val articleIds :ArrayList<Int>,
                        val    titles :ArrayList<String>,
                        val   contents : ArrayList<String>,
                        val        authors :ArrayList<UserAllInfo>,
                        val   is_public_info : ArrayList<Boolean>,
                        val         created_dates : ArrayList<String>,
                        val image_urls : ArrayList<String?>,
                        val context: Context) :RecyclerView.Adapter<ViewHolder>(){

        private val layoutInflater = LayoutInflater.from(context)

         override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
             val DisplayArticleRecyclerviewItemBinding =
                 DisplayArticleRecyclerviewItemModelBinding.inflate(layoutInflater, viewGroup, false)
             return ViewHolder(DisplayArticleRecyclerviewItemBinding.root, DisplayArticleRecyclerviewItemBinding)
         }

         override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

             val article = GeneralArticleModel(articleIds[position], titles[position],
                 contents[position], authors[position],is_public_info[position],created_dates[position],image_urls[position])

             val imageView = viewHolder.itemView.findViewById(R.id.article_list_item_image) as ImageView
             viewHolder.setData(article,imageView)
             //viewHolder.itemView.setOnClickListener { listener.onArticleSelected(article,isGuest,isLoggedInUser,isFeedPage,isFollowing,userId) }
             viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
                 override fun onClick(v: View?) {
                     val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager


                     fragmentTransaction(
                         parentActivityManager,
                         displayArticleFragment.newInstance(article,isGuest,isLoggedInUser,isFeedPage,isFollowing,userId),
                         (containerId!!.id),
                         true,
                         true,
                         false)
                 }
             })
         }

         override fun getItemCount() = titles.size
     }



         internal inner class ViewHolder constructor(itemView: View,
                                                     private val displayArticleListBinding:
                                                     DisplayArticleRecyclerviewItemModelBinding
         ) :
             RecyclerView.ViewHolder(itemView) {
             fun setData(generalArticleModel: GeneralArticleModel,imageView: ImageView) {
                 print("##### SET DATA İÇİ " + generalArticleModel.title)
                 if(generalArticleModel.image != null)
                    Glide.with(activity).load(generalArticleModel.image).into(imageView)
                 else{
                     val imgResId = R.drawable.ic_article_item_icon_image_foreground
                     imageView.setImageResource(imgResId)

                 }
                 displayArticleListBinding.generalArticleModel = generalArticleModel
             }
         }







}
