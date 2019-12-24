package com.project.khajit_app.activity.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.khajit_app.R
import com.project.khajit_app.activity.ui.prediction.MakePredictionFragment
import interfaces.fragmentOperationsInterface

class EquipmentDetailsFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_equipment_details, container, false)
        containerId = container

        val equipmentName = root.findViewById(R.id.details_equipment_name) as TextView
        equipmentName.text = arguments?.getString("equipment")

        val equipmentValue = root.findViewById(R.id.details_equipment_value) as TextView
        equipmentValue.text = arguments?.getString("value")

        val equipmentBuy = root.findViewById(R.id.details_equipment_buy) as Button
        val equipmentSell = root.findViewById(R.id.details_equipment_sell) as Button
        val equipmentPredict = root.findViewById(R.id.details_equipment_predict) as Button
        val equipmentBuyOrder = root.findViewById(R.id.details_equipment_buy_order) as Button
        val equipmentSellOrder = root.findViewById(R.id.details_equipment_sell_order) as Button
        val equipmentFollow = root.findViewById(R.id.details_equipment_follow) as Button

        equipmentBuy.setOnClickListener { goToEquipmentBuy(arguments?.getString("equipment")!!, arguments?.getString("value")!!) }
        equipmentSell.setOnClickListener { goToEquipmentSell(arguments?.getString("equipment")!!, arguments?.getString("value")!!) }
        equipmentPredict.setOnClickListener { goToEquipmentPredict(arguments?.getString("equipment")!!, arguments?.getString("value")!!) }
        equipmentBuyOrder.setOnClickListener { goToEquipmentBuyOrder(arguments?.getString("equipment")!!, arguments?.getString("value")!!) }
        equipmentSellOrder.setOnClickListener { goToEquipmentSellOrder(arguments?.getString("equipment")!!, arguments?.getString("value")!!) }
        equipmentFollow.setOnClickListener { equipmentFollow(arguments?.getString("equipment")!!) }

        return root
    }

    fun goToEquipmentBuy(equipment : String, value : String) {

        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            EquipmentBuyFragment.newInstance(equipment, value),
            (containerId!!.id),
            false,
            true,
            false
        )
    }

    fun goToEquipmentSell(equipment : String, value : String) {

        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            EquipmentSellFragment.newInstance(equipment, value),
            (containerId!!.id),
            false,
            true,
            false
        )
    }

    fun goToEquipmentPredict(equipment : String, value : String) {

        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            MakePredictionFragment.newInstance(equipment, value),
            (containerId!!.id),
            false,
            true,
            false
        )
    }

    fun goToEquipmentBuyOrder(equipment : String, value : String) {

        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            EquipmentBuyOrderFragment.newInstance(equipment, value),
            (containerId!!.id),
            false,
            true,
            false
        )
    }

    fun goToEquipmentSellOrder(equipment : String, value : String) {

        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            EquipmentSellOrderFragment.newInstance(equipment, value),
            (containerId!!.id),
            false,
            true,
            false
        )
    }

    fun equipmentFollow(equipment : String) {
        // TODO

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