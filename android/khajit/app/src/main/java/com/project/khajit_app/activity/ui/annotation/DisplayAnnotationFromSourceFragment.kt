package com.project.khajit_app.activity.ui.annotation

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.project.khajit_app.R
import com.project.khajit_app.data.annotationModels.ShowTextAnnotationModel
import com.project.khajit_app.data.models.UserAllInfo
import com.project.khajit_app.databinding.DisplayAnnotationFragmentRecyclerviewItemBinding

class DisplayAnnotationFromSourceFragment : Fragment() {

    private var annotationResult = ArrayList<ShowTextAnnotationModel>()
    private var startChar = 0
    private var endChar = 0

    private var containerId : ViewGroup? = null
    private lateinit var recyclerView : RecyclerView

    companion object {

        private const val ANNOTATIONRESULTS = "results"
        private const val START = "start"
        private const val END = "end"
        fun newInstance(result: List<ShowTextAnnotationModel>, startChar : Int, endChar : Int): DisplayAnnotationFromSourceFragment {
            val args = Bundle()
            args.putSerializable(ANNOTATIONRESULTS, result as ArrayList<ShowTextAnnotationModel>)
            args.putInt(START,startChar)
            args.putInt(END,endChar)
            val fragment = DisplayAnnotationFromSourceFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: DisplayAnnotationFromSourceViewModel

        override fun onAttach(context: Context) {
            super.onAttach(context)

            annotationResult = arguments!!.getSerializable(ANNOTATIONRESULTS) as ArrayList<ShowTextAnnotationModel>
            startChar = arguments!!.getInt(START)
            endChar = arguments!!.getInt(END)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.display_annotation_from_source_fragment, container,
            false)
        val activity = activity as Context
        containerId = container
        recyclerView = view.findViewById(R.id.list_annotation_fragment_recyclerview) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 1)
        recyclerView.adapter = DisplayAnnotationAdapter(annotationResult,startChar,endChar,activity)


        return view
    }

    internal inner class DisplayAnnotationAdapter(
        val annotationResult : ArrayList<ShowTextAnnotationModel>,
        val startChar : Int,
        val endChar : Int,
        val context: Context) :RecyclerView.Adapter<ViewHolder>(){

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val displayAnnotationRecyclerviewItemBinding = DisplayAnnotationFragmentRecyclerviewItemBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(displayAnnotationRecyclerviewItemBinding.root, displayAnnotationRecyclerviewItemBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val annotationModel = annotationResult[position]

            if((annotationModel.startChar<= startChar && annotationModel.endChar >= startChar)|| (annotationModel.startChar<= endChar && annotationModel.endChar>= endChar))
                viewHolder.setData(annotationModel)
            //viewHolder.itemView.setOnClickListener { listener.onArticleSelected(article,isGuest,isLoggedInUser,isFeedPage,isFollowing,userId) }

        }

        override fun getItemCount() = annotationResult.size
    }



        internal inner class ViewHolder constructor(itemView: View,
                                                    private val displayAnnotationListBinding:
                                                    DisplayAnnotationFragmentRecyclerviewItemBinding
        ) :
            RecyclerView.ViewHolder(itemView) {
            fun setData(showTextAnnotationModel: ShowTextAnnotationModel) {

                var bodyString = ""
                for( bodyItem in showTextAnnotationModel.body){
                    // creating TextView programmatically
                    bodyString += "Comment: " + bodyItem.value
                }
                println("\n " + bodyString )
                var body  = showTextAnnotationModel
                body.bodyFinal = "Name " + body.creator.name!! + "\n " + "\n Text: " + body.annotatedText + "\n " + bodyString
                println(body.bodyFinal)
                displayAnnotationListBinding.textAnnotationModel = body


            }
        }



    }
