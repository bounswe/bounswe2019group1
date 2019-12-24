package com.project.khajit_app.activity.ui.annotation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.khajit_app.R

class CreateAnnotationFragment : Fragment() {

    companion object {
        fun newInstance() = CreateAnnotationFragment()
    }

    private lateinit var viewModel: CreateAnnotationFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_annotation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateAnnotationFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
