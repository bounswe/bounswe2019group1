package com.project.khajit_app.activity.ui.mywallet

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.ContentView
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.activity.LoginPageActivity
import com.project.khajit_app.activity.ui.equipment.EquipmentDetailsFragment
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.activity.ui.profile.UserProfileViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import kotlinx.android.synthetic.main.edit_user_profile_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode

class MyWalletFragment : Fragment(), fragmentOperationsInterface {

    var containerId : ViewGroup? = null
    private lateinit var viewModel: MyWalletFragmentModel

    private lateinit var btc_type: Button
    private lateinit var btc_amount: Button
    private lateinit var btc_wealth: Button
    private lateinit var eth_type: Button
    private lateinit var eth_amount: Button
    private lateinit var eth_wealth: Button
    private lateinit var ltc_type: Button
    private lateinit var ltc_amount: Button
    private lateinit var ltc_wealth: Button
    private lateinit var xag_type: Button
    private lateinit var xag_amount: Button
    private lateinit var xag_wealth: Button
    private lateinit var xau_type: Button
    private lateinit var xau_amount: Button
    private lateinit var xau_wealth: Button
    private lateinit var googl_type: Button
    private lateinit var googl_amount: Button
    private lateinit var googl_wealth: Button
    private lateinit var aapl_type: Button
    private lateinit var aapl_amount: Button
    private lateinit var aapl_wealth: Button
    private lateinit var gm_type: Button
    private lateinit var gm_amount: Button
    private lateinit var gm_wealth: Button
    private lateinit var eur_type: Button
    private lateinit var eur_amount: Button
    private lateinit var eur_wealth: Button
    private lateinit var gbp_type: Button
    private lateinit var gbp_amount: Button
    private lateinit var gbp_wealth: Button
    private lateinit var try_type: Button
    private lateinit var try_amount: Button
    private lateinit var try_wealth: Button
    private lateinit var spy_type: Button
    private lateinit var spy_amount: Button
    private lateinit var spy_wealth: Button
    private lateinit var ivv_type: Button
    private lateinit var ivv_amount: Button
    private lateinit var ivv_wealth: Button
    private lateinit var vti_type: Button
    private lateinit var vti_amount: Button
    private lateinit var vti_wealth: Button
    private lateinit var dji_type: Button
    private lateinit var dji_amount: Button
    private lateinit var dji_wealth: Button
    private lateinit var ixic_type: Button
    private lateinit var ixic_amount: Button
    private lateinit var ixic_wealth: Button
    private lateinit var inx_type: Button
    private lateinit var inx_amount: Button
    private lateinit var inx_wealth: Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this).get(MyWalletFragmentModel::class.java)
        val root = inflater.inflate(R.layout.my_wallet, container, false)
        containerId = container

        btc_type = root.findViewById(R.id.wallet_btc) as Button
        btc_amount = root.findViewById(R.id.wallet_btc_amount) as Button
        btc_wealth = root.findViewById(R.id.wallet_btc_wealth) as Button

        eth_type = root.findViewById(R.id.wallet_eth) as Button
        eth_amount = root.findViewById(R.id.wallet_eth_amount) as Button
        eth_wealth = root.findViewById(R.id.wallet_eth_wealth) as Button

        ltc_type = root.findViewById(R.id.wallet_ltc) as Button
        ltc_amount = root.findViewById(R.id.wallet_ltc_amount) as Button
        ltc_wealth = root.findViewById(R.id.wallet_ltc_wealth) as Button

        xag_type = root.findViewById(R.id.wallet_xag) as Button
        xag_amount = root.findViewById(R.id.wallet_xag_amount) as Button
        xag_wealth = root.findViewById(R.id.wallet_xag_wealth) as Button

        xau_type = root.findViewById(R.id.wallet_xau) as Button
        xau_amount = root.findViewById(R.id.wallet_xau_amount) as Button
        xau_wealth = root.findViewById(R.id.wallet_xau_wealth) as Button

        googl_type = root.findViewById(R.id.wallet_googl) as Button
        googl_amount = root.findViewById(R.id.wallet_googl_amount) as Button
        googl_wealth = root.findViewById(R.id.wallet_googl_wealth) as Button

        aapl_type = root.findViewById(R.id.wallet_aapl) as Button
        aapl_amount = root.findViewById(R.id.wallet_aapl_amount) as Button
        aapl_wealth = root.findViewById(R.id.wallet_aapl_wealth) as Button

        gm_type = root.findViewById(R.id.wallet_gm) as Button
        gm_amount = root.findViewById(R.id.wallet_gm_amount) as Button
        gm_wealth = root.findViewById(R.id.wallet_gm_wealth) as Button

        eur_type = root.findViewById(R.id.wallet_eur) as Button
        eur_amount = root.findViewById(R.id.wallet_eur_amount) as Button
        eur_wealth = root.findViewById(R.id.wallet_eur_wealth) as Button

        gbp_type = root.findViewById(R.id.wallet_gbp) as Button
        gbp_amount = root.findViewById(R.id.wallet_gbp_amount) as Button
        gbp_wealth = root.findViewById(R.id.wallet_gbp_wealth) as Button

        try_type = root.findViewById(R.id.wallet_try) as Button
        try_amount = root.findViewById(R.id.wallet_try_amount) as Button
        try_wealth = root.findViewById(R.id.wallet_try_wealth) as Button

        spy_type = root.findViewById(R.id.wallet_spy) as Button
        spy_amount = root.findViewById(R.id.wallet_spy_amount) as Button
        spy_wealth = root.findViewById(R.id.wallet_spy_wealth) as Button

        ivv_type = root.findViewById(R.id.wallet_ivv) as Button
        ivv_amount = root.findViewById(R.id.wallet_ivv_amount) as Button
        ivv_wealth = root.findViewById(R.id.wallet_ivv_wealth) as Button

        vti_type = root.findViewById(R.id.wallet_vti) as Button
        vti_amount = root.findViewById(R.id.wallet_vti_amount) as Button
        vti_wealth = root.findViewById(R.id.wallet_vti_wealth) as Button

        dji_type = root.findViewById(R.id.wallet_dji) as Button
        dji_amount = root.findViewById(R.id.wallet_dji_amount) as Button
        dji_wealth = root.findViewById(R.id.wallet_dji_wealth) as Button

        ixic_type = root.findViewById(R.id.wallet_ixic) as Button
        ixic_amount = root.findViewById(R.id.wallet_ixic_amount) as Button
        ixic_wealth = root.findViewById(R.id.wallet_ixic_wealth) as Button

        inx_type = root.findViewById(R.id.wallet_inx) as Button
        inx_amount = root.findViewById(R.id.wallet_inx_amount) as Button
        inx_wealth = root.findViewById(R.id.wallet_inx_wealth) as Button

        var btc_val = 0.0
        var eth_val = 0.0
        var ltc_val = 0.0
        var xag_val = 0.0
        var xau_val = 0.0
        var googl_val = 0.0
        var aapl_val = 0.0
        var gm_val = 0.0
        var eur_val = 0.0
        var gbp_val = 0.0
        var try_val = 0.0
        var spy_val = 0.0
        var ivv_val = 0.0
        var vti_val = 0.0
        var dji_val = 0.0
        var ixic_val = 0.0
        var inx_val = 0.0

        var btc_amo = 0.0
        var eth_amo = 0.0
        var ltc_amo = 0.0
        var xag_amo = 0.0
        var xau_amo = 0.0
        var googl_amo = 0.0
        var aapl_amo = 0.0
        var gm_amo = 0.0
        var eur_amo = 0.0
        var gbp_amo = 0.0
        var try_amo = 0.0
        var spy_amo = 0.0
        var ivv_amo = 0.0
        var vti_amo = 0.0
        var dji_amo = 0.0
        var ixic_amo = 0.0
        var inx_amo = 0.0

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
                        btc_amount.text = BigDecimal(response.body()?.BTC!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        eth_amount.text = BigDecimal(response.body()?.ETH!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        ltc_amount.text = BigDecimal(response.body()?.LTC!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        xag_amount.text = BigDecimal(response.body()?.XAG!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        xau_amount.text = BigDecimal(response.body()?.XAU!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        googl_amount.text = BigDecimal(response.body()?.GOOGL!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        aapl_amount.text = BigDecimal(response.body()?.AAPL!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        gm_amount.text = BigDecimal(response.body()?.GM!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        eur_amount.text = BigDecimal(response.body()?.EUR!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        gbp_amount.text = BigDecimal(response.body()?.GBP!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        try_amount.text = BigDecimal(response.body()?.TRY!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        spy_amount.text = BigDecimal(response.body()?.SPY!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        ivv_amount.text = BigDecimal(response.body()?.IVV!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        vti_amount.text = BigDecimal(response.body()?.VTI!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        dji_amount.text = BigDecimal(response.body()?.DJI!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        ixic_amount.text = BigDecimal(response.body()?.IXIC!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
                        inx_amount.text = BigDecimal(response.body()?.INX!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()



                        btc_amo = BigDecimal(response.body()?.BTC!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        eth_amo = BigDecimal(response.body()?.ETH!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        ltc_amo = BigDecimal(response.body()?.LTC!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        xag_amo = BigDecimal(response.body()?.XAG!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        xau_amo = BigDecimal(response.body()?.XAU!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        googl_amo = BigDecimal(response.body()?.GOOGL!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        aapl_amo = BigDecimal(response.body()?.AAPL!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        gm_amo = BigDecimal(response.body()?.GM!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        eur_amo = BigDecimal(response.body()?.EUR!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        gbp_amo = BigDecimal(response.body()?.GBP!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        try_amo = BigDecimal(response.body()?.TRY!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        spy_amo = BigDecimal(response.body()?.SPY!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        ivv_amo = BigDecimal(response.body()?.IVV!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        vti_amo = BigDecimal(response.body()?.VTI!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        dji_amo = BigDecimal(response.body()?.DJI!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        ixic_amo = BigDecimal(response.body()?.IXIC!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        inx_amo = BigDecimal(response.body()?.INX!!.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toDouble()


                        RetrofitClient.instance.retrievePortfolio("11").enqueue(object :
                            Callback<OnePortfolioResponse> {
                            override fun onResponse(
                                call: Call<OnePortfolioResponse>,
                                response: Response<OnePortfolioResponse>
                            ) {
                                println(response.toString())
                                if(response.code() == 200 ){
                                    if(response.body()?.detail != null){
                                        println("NOT CHANGED")
                                    }else{
                                        println("CHANGED")
                                        if(response.body()?.BTC != null){
                                            btc_val = response.body()?.BTC!!
                                            btc_wealth.text = "$" + BigDecimal((btc_val * btc_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.LTC != null){
                                            ltc_val = response.body()?.LTC!!
                                            ltc_wealth.text = "$" + BigDecimal((ltc_val * ltc_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.ETH != null){
                                            eth_val = response.body()?.ETH!!
                                            eth_wealth.text = "$" + BigDecimal((eth_val * eth_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.XAG != null){
                                            xag_val = response.body()?.XAG!!
                                            xag_wealth.text = "$" + BigDecimal((xag_val * xag_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.XAU != null){
                                            xau_val = response.body()?.XAU!!
                                            xau_wealth.text = "$" + BigDecimal((xau_val * xau_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.GOOGL != null){
                                            googl_val = response.body()?.GOOGL!!
                                            googl_wealth.text = "$" + BigDecimal((googl_val * googl_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.AAPL != null){
                                            aapl_val = response.body()?.AAPL!!
                                            aapl_wealth.text = "$" + BigDecimal((aapl_val * aapl_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.GM != null){
                                            gm_val = response.body()?.GM!!
                                            gm_wealth.text = "$" + BigDecimal((gm_val * gm_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.EUR != null){
                                            eur_val = response.body()?.EUR!!
                                            eur_val = BigDecimal(1/(eur_val!!)).setScale(4, RoundingMode.HALF_EVEN).toDouble()
                                            eur_wealth.text = "$" + BigDecimal((eur_val * eur_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.GBP != null){
                                            gbp_val = response.body()?.GBP!!
                                            gbp_val = BigDecimal(1/(gbp_val!!)).setScale(4, RoundingMode.HALF_EVEN).toDouble()
                                            gbp_wealth.text = "$" + BigDecimal((gbp_val * gbp_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.TRY != null){
                                            try_val = response.body()?.TRY!!
                                            try_val = BigDecimal(1/(try_val!!)).setScale(4, RoundingMode.HALF_EVEN).toDouble()
                                            try_wealth.text = "$" + BigDecimal((try_val * try_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.DJI != null){
                                            dji_val = response.body()?.DJI!!
                                            dji_wealth.text = "$" + BigDecimal((dji_val * dji_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.IXIC != null){
                                            ixic_val = response.body()?.IXIC!!
                                            ixic_wealth.text = "$" + BigDecimal((dji_val * dji_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.INX != null){
                                            inx_val = response.body()?.INX!!
                                            inx_wealth.text = "$" + BigDecimal((inx_val * inx_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.SPY != null){
                                            spy_val = response.body()?.SPY!!.substring(1).toDouble()
                                            spy_wealth.text = "$" + BigDecimal((spy_val * spy_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.IVV != null){
                                            ivv_val = response.body()?.IVV!!.substring(1).toDouble()
                                            ivv_wealth.text = "$" + BigDecimal((ivv_val * ivv_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }
                                        if(response.body()?.VTI != null){
                                            vti_val = response.body()?.VTI!!.substring(1).toDouble()
                                            vti_wealth.text = "$" + BigDecimal((vti_val * vti_amo)).setScale(2, RoundingMode.HALF_EVEN).toString()
                                        }

                                    }
                                }else{

                                }
                            }
                            override fun onFailure(call: Call<OnePortfolioResponse>, t: Throwable) {

                            }
                        })


                    }
                }else{

                }
            }
            override fun onFailure(call: Call<WalletResponse>, t: Throwable) {

            }
        })


        btc_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("BTC", btc_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        eth_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("ETH", eth_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        ltc_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("LTC", ltc_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        xag_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("XAG", xag_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        xau_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("XAU", xau_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        googl_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("GOOGL", googl_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        aapl_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("BTC", aapl_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        gm_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("GM", gm_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        eur_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("EUR", eur_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        gbp_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("GBP", gbp_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        try_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("TRY", try_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        spy_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("SPY", spy_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        ivv_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("IVV", ivv_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        vti_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("VTI", vti_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        dji_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("DJI", dji_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        ixic_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("IXIC", ixic_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        inx_type.setOnClickListener { root ->
            val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager
            fragmentTransaction(
                parentActivityManager,
                EquipmentDetailsFragment.newInstance("INX", inx_val.toString(),0),
                (containerId!!.id),
                true,
                true,
                false
            )
        }
        return root
    }

    companion object {
        fun newInstance(): MyWalletFragment {
            val fragmentEditUser = MyWalletFragment()
            val args = Bundle()
            fragmentEditUser.arguments = args
            return fragmentEditUser
        }

    }
}

