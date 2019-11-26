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
        val value_rhenium = root.findViewById(R.id.value_rhenium) as TextView

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
                    value_eur.text = response?.body()?.value_eur
                    value_gbp.text = response?.body()?.value_gbp
                    value_try.text = response?.body()?.value_try
                }

                override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

        RetrofitClient.instance.cryptoValues()
            .enqueue(object : Callback<CryptoResponse> {
                override fun onResponse(
                    call: Call<CryptoResponse>?,
                    response: Response<CryptoResponse>?
                ) {
                    value_btc.text = response?.body()?.value_btc
                    value_eth.text = response?.body()?.value_eth
                    value_ltc.text = response?.body()?.value_ltc
                }

                override fun onFailure(call: Call<CryptoResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

        RetrofitClient.instance.stockValues()
            .enqueue(object : Callback<List<Stock>> {
                override fun onResponse(
                    call: Call<List<Stock>>?,
                    response: Response<List<Stock>>?
                ) {
                    value_googl.text = response?.body()?.get(0)?.price
                    value_aapl.text = response?.body()?.get(1)?.price
                    value_gm.text = response?.body()?.get(2)?.price
                }

                override fun onFailure(call: Call<List<Stock>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

        RetrofitClient.instance.commodityValues()
            .enqueue(object : Callback<CommodityResponse> {
                override fun onResponse(
                    call: Call<CommodityResponse>?,
                    response: Response<CommodityResponse>?
                ) {
                    value_gold.text = response?.body()?.value_xau
                    value_silver.text = response?.body()?.value_xag
                    value_rhenium.text = response?.body()?.value_xrh
                }

                override fun onFailure(call: Call<CommodityResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

        RetrofitClient.instance.tradeValues()
            .enqueue(object : Callback<TradeIndiceResponse> {
                override fun onResponse(
                    call: Call<TradeIndiceResponse>?,
                    response: Response<TradeIndiceResponse>?
                ) {
                    value_dji.text = response?.body()?.majorIndexesList?.get(0)?.price
                    value_ixic.text = response?.body()?.majorIndexesList?.get(1)?.price
                    value_inx.text = response?.body()?.majorIndexesList?.get(2)?.price
                }

                override fun onFailure(call: Call<TradeIndiceResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

        RetrofitClient.instance.etfValues()
            .enqueue(object : Callback<ETFResponse> {
                override fun onResponse(
                    call: Call<ETFResponse>?,
                    response: Response<ETFResponse>?
                ) {
                    value_spy.text = response?.body()?.obj_spy?.price
                    value_ivv.text = response?.body()?.obj_ivv?.price
                    value_vti.text = response?.body()?.obj_vti?.price
                }

                override fun onFailure(call: Call<ETFResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

            })

        // define variables for card views
        val eurCard = root.findViewById(R.id.equipment_fragment_eur_card) as CardView
        val gbpCard = root.findViewById(R.id.equipment_fragment_gbp_card) as CardView
        val tryCard = root.findViewById(R.id.equipment_fragment_try_card) as CardView
        val btcCard = root.findViewById(R.id.equipment_fragment_btc_card) as CardView
        val ethCard = root.findViewById(R.id.equipment_fragment_eth_card) as CardView
        val ltcCard = root.findViewById(R.id.equipment_fragment_ltc_card) as CardView

        // add listeners to the equipment cards
        eurCard.setOnClickListener { goToEquipmentDetails("EUR", value_eur.text.toString()) }
        gbpCard.setOnClickListener { goToEquipmentDetails("GBP", value_gbp.text.toString()) }
        tryCard.setOnClickListener { goToEquipmentDetails("TRY", value_try.text.toString()) }
        btcCard.setOnClickListener { goToEquipmentDetails("BTC", value_btc.text.toString()) }
        ethCard.setOnClickListener { goToEquipmentDetails("ETH", value_eth.text.toString()) }
        ltcCard.setOnClickListener { goToEquipmentDetails("LTC", value_ltc.text.toString()) }

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

    fun commodityXAU(view: View) {

    }

    fun commodityXAG(view: View) {

    }

    fun commodityXRH(view: View) {

    }

    fun stockGOOGL(view: View) {

    }

    fun stockAAPL(view: View) {

    }

    fun stockGM(view: View) {

    }

    fun tradeDJI(view: View) {

    }

    fun tradeIXIC(view: View) {

    }

    fun tradeINX(view: View) {

    }

    fun etfSPY(view: View) {

    }

    fun etfIVV(view: View) {

    }

    fun etfVTI(view: View) {

    }
}