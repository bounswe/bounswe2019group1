package com.project.khajit_app.activity.ui.article

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

import com.project.khajit_app.R
import com.project.khajit_app.data.models.GeneralArticleModel
import com.project.khajit_app.databinding.DisplayArticleFragmentBinding

class displayArticleFragment : Fragment() {

    private var isGuest : Int = 0
    private var isLoggedInUser : Int = 0
    private var isFeedPage: Int = 0
    private var isFollowing : Int = 0
    private var userId : Int = 0

    companion object {

        private const val ARTICLEMODEL = "model"
        private const val ISGUEST = "isGuest"
        private const val ISLOGGEDINUSER = "isLoggedInUser"
        private const val ISFEEDPAGE = "isFeedPage"
        private  const val ISFOLLOWING = "isFollowing"
        private  const val USERID = "userId"

        fun newInstance(generalArticleModel: GeneralArticleModel,isGuest : Int,isLoggedInUser : Int,isFeedPage: Int, isFollowing : Int, userId: Int): displayArticleFragment {
            val args = Bundle()
            args.putSerializable(ARTICLEMODEL, generalArticleModel)
            args.putInt(ISGUEST,isGuest)
            args.putInt(ISLOGGEDINUSER,isLoggedInUser)
            args.putInt(ISFEEDPAGE,isFeedPage)
            args.putInt(ISFOLLOWING,isFollowing)
            args.putInt(USERID,userId)
            val fragment = displayArticleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: DisplayArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.display_article_fragment, container, false)

        val DisplayArticleFragmentBinding =
            DisplayArticleFragmentBinding.inflate(inflater, container, false)

        val articleModel = arguments!!.getSerializable(ARTICLEMODEL) as GeneralArticleModel
        isGuest = arguments!!.getInt(ISGUEST)
        isLoggedInUser = arguments!!.getInt(ISLOGGEDINUSER)
        isFeedPage = arguments!!.getInt(ISFEEDPAGE)
        isFollowing = arguments!!.getInt(ISFOLLOWING)
        userId = arguments!!.getInt(USERID)
        val imageView = DisplayArticleFragmentBinding.articleImageDisplayDetails



        if(articleModel.image != null)
            Glide.with(activity).load(articleModel.image).into(imageView)
        else{
            val imgResId = R.drawable.ic_article_image_no_content_foreground
            imageView.setImageResource(imgResId)

        }

        DisplayArticleFragmentBinding.generalArticleModel = articleModel
        //model.text = String.format(getString(R.string.description_format), model.description, model.url)
        return DisplayArticleFragmentBinding.root
    }



}
