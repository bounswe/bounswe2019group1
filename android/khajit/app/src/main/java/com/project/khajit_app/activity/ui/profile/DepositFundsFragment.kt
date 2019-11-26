package com.project.khajit_app.activity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.DepositFundsModel
import com.project.khajit_app.data.models.DepositFundsResponse
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepositFundsFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val root = inflater.inflate(R.layout.fragment_deposit_funds, container, false)
        containerId = container

        val depositAmount = root.findViewById(R.id.funds_deposit_amount) as EditText
        val depositButton = root.findViewById(R.id.funds_deposit_button) as Button

        depositButton.setOnClickListener {
            // send a request to the backend api to deposit depositAmount amount of USD to the user's wallet.
            val depositModel = DepositFundsModel(depositAmount.text.toString().toInt())

            RetrofitClient.instance.depositFunds(depositModel)
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
        fun newInstance(): DepositFundsFragment {
            val fragmentDepositFunds = DepositFundsFragment()
            val args = Bundle()
            fragmentDepositFunds.arguments = args
            return fragmentDepositFunds
        }

    }
}