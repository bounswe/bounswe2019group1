package com.project.khajit_app.activity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.project.khajit_app.R
import interfaces.fragmentOperationsInterface

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