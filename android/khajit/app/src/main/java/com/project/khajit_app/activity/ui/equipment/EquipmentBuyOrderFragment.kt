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

class EquipmentBuyOrderFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_buy_order_equipment, container, false)
        containerId = container

        val equipmentName = root.findViewById(R.id.buy_order_equipment_name) as TextView
        equipmentName.text = "BUY ORDER: " + arguments?.getString("equipment")

        val equipmentValue = root.findViewById(R.id.buy_order_equipment_value) as TextView
        equipmentValue.text = "current: " + arguments?.getString("value")

        val buyValue = root.findViewById(R.id.equipment_buy_order_value) as EditText
        val buyAmount = root.findViewById(R.id.equipment_buy_order_amount) as EditText
        val buyButton = root.findViewById(R.id.equipment_buy_order_button) as Button

        buyButton.setOnClickListener {
            // send a request to the api to set sell order
            val sellModel = EquipmentBSOrderModel(arguments?.getString("equipment")!!, buyValue.text.toString().toDouble(), buyAmount.text.toString().toInt())

            RetrofitClient.instance.createBuyOrder(sellModel)
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
        fun newInstance(equipment : String, value : String): EquipmentBuyOrderFragment {
            val fragmentEquipmentBuyOrder = EquipmentBuyOrderFragment()
            val args = Bundle()
            args.putSerializable("equipment",equipment)
            args.putSerializable("value",value)
            fragmentEquipmentBuyOrder.arguments = args
            return fragmentEquipmentBuyOrder
        }

    }
}