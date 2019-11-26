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
import com.project.khajit_app.data.models.DepositFundsResponse
import com.project.khajit_app.data.models.EquipmentBSModel
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EquipmentBuyFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_buy_equipment, container, false)
        containerId = container

        val equipmentName = root.findViewById(R.id.buy_equipment_name) as TextView
        equipmentName.text = "BUY: " + arguments?.getString("equipment")

        val equipmentValue = root.findViewById(R.id.buy_equipment_value) as TextView
        equipmentValue.text = "from: " + arguments?.getString("value")

        val buyAmount = root.findViewById(R.id.equipment_buy_amount) as EditText
        val buyButton = root.findViewById(R.id.equipment_buy_button) as Button

        buyButton.setOnClickListener {
            // send a request to the api to buy buyAmount.text amount of equipment.
            val buyModel = EquipmentBSModel(arguments?.getString("equipment")!!, buyAmount.text.toString().toInt())
            RetrofitClient.instance.buyEquipment(buyModel)
                .enqueue(object : Callback<DepositFundsResponse> {
                    override fun onResponse(
                        call: Call<DepositFundsResponse>?,
                        response: Response<DepositFundsResponse>?
                    ) {
                        Toast.makeText(context, "Current wallet balance: " + response?.body()?.current_wallet_balance, Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<DepositFundsResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }

        return root
    }


    companion object {
        fun newInstance(equipment : String, value : String): EquipmentBuyFragment {
            val fragmentEquipmentBuy = EquipmentBuyFragment()
            val args = Bundle()
            args.putSerializable("equipment",equipment)
            args.putSerializable("value",value)
            fragmentEquipmentBuy.arguments = args
            return fragmentEquipmentBuy
        }

    }
}