package com.project.khajit_app.activity.ui.article

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.project.khajit_app.R
import com.project.khajit_app.data.models.CreateArticleModel
import interfaces.fragmentOperationsInterface

class createArticleFragment : Fragment(), fragmentOperationsInterface {



    private lateinit var viewModel: CreateArticleViewModel
    private lateinit var titleOfArticle : EditText
    private lateinit var contentOfArticle : EditText
    private lateinit var email_input : EditText
    private var isPublic : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createArticleRoot = inflater.inflate(R.layout.create_article_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(CreateArticleViewModel::class.java)


        titleOfArticle = createArticleRoot.findViewById(R.id.createArticleFragmentTitle) as EditText
        contentOfArticle = createArticleRoot.findViewById(R.id.createArticleFragmentContent) as EditText
        val checkBox = createArticleRoot.findViewById(R.id.createArticleFragmentCheckBox) as CheckBox


        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                isPublic = false
            }
        }


        var publish_button =  createArticleRoot.findViewById(R.id.createArticleFragmentPublishContent) as Button
        publish_button.setOnClickListener(publishButtonListener)



        return createArticleRoot
    }
    private val publishButtonListener = View.OnClickListener { view ->
        var title_info = titleOfArticle.text.toString().trim()
        var content_info = contentOfArticle.text.toString().trim()
        if (title_info.isEmpty()) {
            titleOfArticle.error = "Title is required."
            titleOfArticle.requestFocus()
            return@OnClickListener
        }
        if (content_info.isEmpty()) {
            contentOfArticle.error = "Content is required."
            contentOfArticle.requestFocus()
            return@OnClickListener
        }
        val userInfo = CreateArticleModel(title_info,content_info,isPublic)
        println(userInfo.title)
        println(userInfo.content)
        println(userInfo.is_public)
        val parentActivityManager: FragmentManager =
            activity?.supportFragmentManager as FragmentManager
        removeFragment(parentActivityManager)

    }
    companion object {
        fun newInstance(): createArticleFragment {
            val ArticleFragment = createArticleFragment()
            val args = Bundle()
            ArticleFragment.arguments = args
            return ArticleFragment
        }
    }


}
