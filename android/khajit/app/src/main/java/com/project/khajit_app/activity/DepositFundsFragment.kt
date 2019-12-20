package com.project.khajit_app.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.project.khajit_app.R
import com.project.khajit_app.activity.ui.followlist.FollowListFragment
import com.project.khajit_app.activity.ui.mywallet.MyWalletFragment
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.DepositFundsModel
import com.project.khajit_app.data.models.DepositFundsResponse
import com.project.khajit_app.data.models.WalletResponse
import com.project.khajit_app.global.User
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

        val current_usd = root.findViewById(R.id.current_funds) as TextView
        val wealth_usd = root.findViewById(R.id.wealth_funds) as TextView
        val sent_usd = root.findViewById(R.id.sent_funds) as TextView
        val balance = root.findViewById(R.id.networth_funds) as TextView

        if(!User.type!!) {
            val parentActivityManager: FragmentManager =
                activity?.supportFragmentManager as FragmentManager
            removeFragment(parentActivityManager)
            Toast.makeText(context, "Only Traders can access my wallet", Toast.LENGTH_LONG).show()
            return root
        }

        RetrofitClient.instance.myWallet().enqueue(object :
            Callback<WalletResponse> {
            override fun onResponse(
                call: Call<WalletResponse>,
                response: Response<WalletResponse>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT Upgraded")
                    }else{
                        current_usd.text = "Current: $" + response.body()?.USD.toString()
                        wealth_usd.text = "My wealth: $" + response.body()?.Wealth_USD.toString()
                        sent_usd.text = "Total load: $" + response.body()?.Sent_USD.toString()
                        var wealth = 0.0
                        var sent = 0.0
                        wealth = response.body()?.Wealth_USD?.toDouble()!!
                        sent = response.body()?.Sent_USD?.toDouble()!!
                        if(sent- wealth > 0) {
                            balance.text = "Current networth: -$" + (sent-wealth).toString()
                        } else {
                            balance.text = "Current networth: $" + (wealth-sent).toString()
                        }
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {

            }
        })

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

        var wallet_btn = root.findViewById(R.id.mywallet_button) as Button
        wallet_btn.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                MyWalletFragment.newInstance(),
                (containerId!!.id),
                true,
                true,
                false
            )
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