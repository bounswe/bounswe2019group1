package com.project.khajit_app.activity.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.ui.home.HomeViewModel

class EquipmentFragment : Fragment() {

    private lateinit var equipmentViewModel: EquipmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        equipmentViewModel =
            ViewModelProviders.of(this).get(EquipmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_equipment, container, false)

        var loader = root.findViewById(R.id.progress_loader) as ProgressBar
        loader.visibility = View.GONE

        return root
    }
}