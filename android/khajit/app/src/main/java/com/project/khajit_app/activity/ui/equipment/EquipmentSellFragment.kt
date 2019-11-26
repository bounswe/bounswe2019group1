package com.project.khajit_app.activity.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.project.khajit_app.R
import interfaces.fragmentOperationsInterface

class EquipmentSellFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_sell_equipment, container, false)
        containerId = container

        val equipmentName = root.findViewById(R.id.buy_equipment_name) as TextView
        equipmentName.text = "SELL: " + arguments?.getString("equipment")

        val equipmentValue = root.findViewById(R.id.buy_equipment_value) as TextView
        equipmentValue.text = "from: $" + arguments?.getString("value")

        return root
    }


    companion object {
        fun newInstance(equipment : String, value : String): EquipmentDetailsFragment {
            val fragmentEquipmentDetails = EquipmentDetailsFragment()
            val args = Bundle()
            args.putSerializable("equipment",equipment)
            args.putSerializable("value",value)
            fragmentEquipmentDetails.arguments = args
            return fragmentEquipmentDetails
        }

    }
}