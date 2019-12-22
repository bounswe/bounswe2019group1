package com.project.khajit_app.activity.ui.event

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.project.khajit_app.R
import com.project.khajit_app.data.models.EventModel
import com.project.khajit_app.databinding.DisplayEventModelFragmentBinding

class DisplayEventModel : Fragment() {
    companion object {

        private const val EVENTMODEL = "model"

        fun newInstance(eventModel: EventModel): DisplayEventModel {
            val args = Bundle()
            args.putSerializable(EVENTMODEL, eventModel)
            val fragment = DisplayEventModel()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: DisplayEventModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.display_event_model_fragment, container, false)

        val DisplayEventFragmentBinding =
            DisplayEventModelFragmentBinding.inflate(inflater, container, false)

        val eventModel = arguments!!.getSerializable(EVENTMODEL) as EventModel
        DisplayEventFragmentBinding.eventModel = eventModel
        //model.text = String.format(getString(R.string.description_format), model.description, model.url)
        return DisplayEventFragmentBinding.root
    }


}
