package com.project.khajit_app.activity.ui.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.project.khajit_app.R
import com.project.khajit_app.activity.ui.home.HomeViewModel
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import interfaces.fragmentOperationsInterface
import kotlinx.android.synthetic.main.fragment_equipment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EquipmentFragment : Fragment(), fragmentOperationsInterface {

    private lateinit var equipmentViewModel: EquipmentViewModel
    var containerId : ViewGroup? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        equipmentViewModel =
            ViewModelProviders.of(this).get(EquipmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_equipment, container, false)
        containerId = container

        // define variables for equipment textviews

        val value_eur = root.findViewById(R.id.value_eur) as TextView
        val value_gbp = root.findViewById(R.id.value_gbp) as TextView
        val value_try = root.findViewById(R.id.value_try) as TextView

        val value_gold = root.findViewById(R.id.value_gold) as TextView
        val value_silver = root.findViewById(R.id.value_silver) as TextView
        //val value_rhenium = root.findViewById(R.id.value_rhenium) as TextView

        val value_btc = root.findViewById(R.id.value_btc) as TextView
        val value_eth = root.findViewById(R.id.value_eth) as TextView
        val value_ltc = root.findViewById(R.id.value_ltc) as TextView

        val value_googl = root.findViewById(R.id.value_googl) as TextView
        val value_aapl = root.findViewById(R.id.value_aapl) as TextView
        val value_gm = root.findViewById(R.id.value_gm) as TextView

        val value_dji = root.findViewById(R.id.value_dji) as TextView
        val value_ixic = root.findViewById(R.id.value_ixic) as TextView
        val value_inx = root.findViewById(R.id.value_inx) as TextView

        val value_spy = root.findViewById(R.id.value_spy) as TextView
        val value_ivv = root.findViewById(R.id.value_ivv) as TextView
        val value_vti = root.findViewById(R.id.value_vti) as TextView

        // request current values of equipments from the backend

        RetrofitClient.instance.currencyValues()
            .enqueue(object : Callback<CurrencyResponse> {
                override fun onResponse(
                    call: Call<CurrencyResponse>?,
                    response: Response<CurrencyResponse>?
                ) {
                    value_eur.text = "$"+(1/(response?.body()?.value_eur!!.toDouble())).toString().substring(0, 6)
                    value_gbp.text = "$"+(1/(response?.body()?.value_gbp!!.toDouble())).toString().substring(0, 6)
                    value_try.text = "$"+(1/(response?.body()?.value_try!!.toDouble())).toString().substring(0, 6)
                }

                override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                    println(t.message)
                }

            })

        RetrofitClient.instance.cryptoValues()
            .enqueue(object : Callback<CryptoResponse> {
                override fun onResponse(
                    call: Call<CryptoResponse>?,
                    response: Response<CryptoResponse>?
                ) {
                    value_btc.text = "$"+response?.body()?.value_btc?.substring(0, 8)
                    value_eth.text = "$"+response?.body()?.value_eth?.substring(0, 8)
                    value_ltc.text = "$"+response?.body()?.value_ltc?.substring(0, 8)
                }

                override fun onFailure(call: Call<CryptoResponse>, t: Throwable) {
                    println(t.message)
                }

            })

        RetrofitClient.instance.stockValues()
            .enqueue(object : Callback<StockResponse> {
                override fun onResponse(
                    call: Call<StockResponse>?,
                    response: Response<StockResponse>?
                ) {
                    value_googl.text = "$"+response?.body()?.value_googl?.substring(0, 8)
                    value_aapl.text = "$"+response?.body()?.value_aapl?.substring(0, 8)
                    value_gm.text = "$"+response?.body()?.value_gm?.substring(0, 8)
                }

                override fun onFailure(call: Call<StockResponse>, t: Throwable) {
                    println(t.message)
                }

            })

        RetrofitClient.instance.commodityValues()
            .enqueue(object : Callback<CommodityResponse> {
                override fun onResponse(
                    call: Call<CommodityResponse>?,
                    response: Response<CommodityResponse>?
                ) {
                    value_gold.text = "$"+response?.body()?.value_xau?.substring(0, 8)
                    value_silver.text = "$"+response?.body()?.value_xag?.substring(0, 8)
                    //value_rhenium.text = response?.body()?.value_xrh
                }

                override fun onFailure(call: Call<CommodityResponse>, t: Throwable) {
                    println(t.message)
                }

            })

        RetrofitClient.instance.tradeValues()
            .enqueue(object : Callback<TradeIndiceResponse> {
                override fun onResponse(
                    call: Call<TradeIndiceResponse>?,
                    response: Response<TradeIndiceResponse>?
                ) {
                    value_dji.text = "$"+response?.body()?.value_dji?.substring(0, 8)
                    value_ixic.text = "$"+response?.body()?.value_ixic?.substring(0, 8)
                    value_inx.text = "$"+response?.body()?.value_inx?.substring(0, 8)
                }

                override fun onFailure(call: Call<TradeIndiceResponse>, t: Throwable) {
                    println(t.message)
                }

            })

        RetrofitClient.instance.etfValues()
            .enqueue(object : Callback<ETFResponse> {
                override fun onResponse(
                    call: Call<ETFResponse>?,
                    response: Response<ETFResponse>?
                ) {
                    value_spy.text = response?.body()?.obj_spy
                    value_ivv.text = response?.body()?.obj_ivv
                    value_vti.text = response?.body()?.obj_vti
                }

                override fun onFailure(call: Call<ETFResponse>, t: Throwable) {
                    println(t.message)
                }

            })

        // define variables for card views
        val eurCard = root.findViewById(R.id.equipment_fragment_eur_card) as CardView
        val gbpCard = root.findViewById(R.id.equipment_fragment_gbp_card) as CardView
        val tryCard = root.findViewById(R.id.equipment_fragment_try_card) as CardView
        val btcCard = root.findViewById(R.id.equipment_fragment_btc_card) as CardView
        val ethCard = root.findViewById(R.id.equipment_fragment_eth_card) as CardView
        val ltcCard = root.findViewById(R.id.equipment_fragment_ltc_card) as CardView
        val xauCard = root.findViewById(R.id.equipment_fragment_xau_card) as CardView
        val xagCard = root.findViewById(R.id.equipment_fragment_xag_card) as CardView
        val googlCard = root.findViewById(R.id.equipment_fragment_googl_card) as CardView
        val aaplCard = root.findViewById(R.id.equipment_fragment_aapl_card) as CardView
        val gmCard = root.findViewById(R.id.equipment_fragment_gm_card) as CardView
        val djiCard = root.findViewById(R.id.equipment_fragment_dji_card) as CardView
        val ixicCard = root.findViewById(R.id.equipment_fragment_ixic_card) as CardView
        val inxCard = root.findViewById(R.id.equipment_fragment_inx_card) as CardView
        val spyCard = root.findViewById(R.id.equipment_fragment_spy_card) as CardView
        val ivvCard = root.findViewById(R.id.equipment_fragment_ivv_card) as CardView
        val vtiCard = root.findViewById(R.id.equipment_fragment_vti_card) as CardView


        // add listeners to the equipment cards
        eurCard.setOnClickListener { goToEquipmentDetails("EUR", value_eur.text.toString()) }
        gbpCard.setOnClickListener { goToEquipmentDetails("GBP", value_gbp.text.toString()) }
        tryCard.setOnClickListener { goToEquipmentDetails("TRY", value_try.text.toString()) }
        btcCard.setOnClickListener { goToEquipmentDetails("BTC", value_btc.text.toString()) }
        ethCard.setOnClickListener { goToEquipmentDetails("ETH", value_eth.text.toString()) }
        ltcCard.setOnClickListener { goToEquipmentDetails("LTC", value_ltc.text.toString()) }
        xauCard.setOnClickListener { goToEquipmentDetails("XAU", value_gold.text.toString()) }
        xagCard.setOnClickListener { goToEquipmentDetails("XAG", value_silver.text.toString()) }
        googlCard.setOnClickListener { goToEquipmentDetails("GOOGL", value_googl.text.toString()) }
        aaplCard.setOnClickListener { goToEquipmentDetails("AAPL", value_aapl.text.toString()) }
        gmCard.setOnClickListener { goToEquipmentDetails("GM", value_gm.text.toString()) }
        djiCard.setOnClickListener { goToEquipmentDetails("DJI", value_dji.text.toString()) }
        ixicCard.setOnClickListener { goToEquipmentDetails("IXIC", value_ixic.text.toString()) }
        inxCard.setOnClickListener { goToEquipmentDetails("INX", value_inx.text.toString()) }
        spyCard.setOnClickListener { goToEquipmentDetails("SPY", value_spy.text.toString()) }
        ivvCard.setOnClickListener { goToEquipmentDetails("IVV", value_ivv.text.toString()) }
        vtiCard.setOnClickListener { goToEquipmentDetails("VTI", value_vti.text.toString()) }

        return root
    }

    fun goToEquipmentDetails(equipment : String, value : String) {

        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            EquipmentDetailsFragment.newInstance(equipment, value),
            (containerId!!.id),
            true,
            true,
            false
        )
    }

}