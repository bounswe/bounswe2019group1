package com.project.khajit_app.activity.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.EquipmentBSOrderModel
import com.project.khajit_app.data.models.EquipmentBSOrderResponse
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EquipmentSellOrderFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_sell_order_equipment, container, false)
        containerId = container

        val equipmentName = root.findViewById(R.id.sell_order_equipment_name) as TextView
        equipmentName.text = "SELL ORDER: " + arguments?.getString("equipment")

        val equipmentValue = root.findViewById(R.id.sell_order_equipment_value) as TextView
        equipmentValue.text = "current: " + arguments?.getString("value")

        val sellValue = root.findViewById(R.id.equipment_sell_order_value) as EditText
        val sellAmount = root.findViewById(R.id.equipment_sell_order_amount) as EditText
        val sellButton = root.findViewById(R.id.equipment_sell_order_button) as Button

        sellButton.setOnClickListener {
            // send a request to the api to set sell order
            val sellModel = EquipmentBSOrderModel(arguments?.getString("equipment")!!, sellValue.text.toString().toDouble(), sellAmount.text.toString().toInt())

            RetrofitClient.instance.createSellOrder(sellModel)
                .enqueue(object : Callback<EquipmentBSOrderResponse> {
                    override fun onResponse(
                        call: Call<EquipmentBSOrderResponse>?,
                        response: Response<EquipmentBSOrderResponse>?
                    ) {
                        var orderValue = response?.body()?.amount as String
                        var dotPosition = orderValue.indexOf('.')
                        orderValue = orderValue.substring(0, dotPosition+3)
                        Toast.makeText(context, "Order placed for: $" + orderValue, Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<EquipmentBSOrderResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }


        return root
    }


    companion object {
        fun newInstance(equipment : String, value : String): EquipmentSellOrderFragment {
            val fragmentEquipmentSellOrder = EquipmentSellOrderFragment()
            val args = Bundle()
            args.putSerializable("equipment",equipment)
            args.putSerializable("value",value)
            fragmentEquipmentSellOrder.arguments = args
            return fragmentEquipmentSellOrder
        }

    }
}