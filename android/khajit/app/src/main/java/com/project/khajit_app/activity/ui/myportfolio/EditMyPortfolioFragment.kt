package com.project.khajit_app.activity.ui.myportfolio

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import com.project.khajit_app.activity.FollowListViewAdapter
import com.project.khajit_app.activity.OnePortfolioViewAdapter
import com.project.khajit_app.activity.PortfolioListAdapter
import com.project.khajit_app.activity.ui.other_profile.OtherUserProfile
import com.project.khajit_app.activity.ui.profile.UserProfile
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.*
import com.project.khajit_app.global.User
import interfaces.fragmentOperationsInterface
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode

class EditMyPortfolioFragment : Fragment(), fragmentOperationsInterface {
    var containerId : ViewGroup? = null

    private lateinit var myportfolioModel: EditMyPortfolioModel

    private lateinit var edit_back: Button
    private lateinit var edit_change: Button
    private lateinit var edit_delete: Button

    private lateinit var input_port_name: EditText
    private lateinit var BTC: CheckBox
    private lateinit var LTC: CheckBox
    private lateinit var ETH: CheckBox
    private lateinit var XAG: CheckBox
    private lateinit var XAU: CheckBox
    private lateinit var GOOGL: CheckBox
    private lateinit var AAPL: CheckBox
    private lateinit var GM: CheckBox
    private lateinit var EUR: CheckBox
    private lateinit var GBP: CheckBox
    private lateinit var TRY: CheckBox
    private lateinit var DJI: CheckBox
    private lateinit var IXIC: CheckBox
    private lateinit var INX: CheckBox
    private lateinit var SPY: CheckBox
    private lateinit var IVV: CheckBox
    private lateinit var VTI: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        myportfolioModel =
            ViewModelProviders.of(this).get(EditMyPortfolioModel::class.java)

        val root = inflater.inflate(R.layout.activity_edit_myportfolio, container, false)
        containerId = container

        var name = ""
        name = arguments?.getString("name")!!
        var id = ""
        id = arguments?.getString("id")!!

        input_port_name = root.findViewById(R.id.input_port_name) as EditText
        BTC = root.findViewById(R.id.eq_BTC) as CheckBox
        LTC = root.findViewById(R.id.eq_LTC) as CheckBox
        ETH = root.findViewById(R.id.eq_ETH) as CheckBox
        XAG = root.findViewById(R.id.eq_XAG) as CheckBox
        XAU = root.findViewById(R.id.eq_XAU) as CheckBox
        GOOGL = root.findViewById(R.id.eq_GOOGL) as CheckBox
        AAPL = root.findViewById(R.id.eq_AAPL) as CheckBox
        GM = root.findViewById(R.id.eq_GM) as CheckBox
        EUR = root.findViewById(R.id.eq_EUR) as CheckBox
        GBP = root.findViewById(R.id.eq_GBP) as CheckBox
        TRY = root.findViewById(R.id.eq_TRY) as CheckBox
        DJI = root.findViewById(R.id.eq_DJI) as CheckBox
        IXIC = root.findViewById(R.id.eq_IXIC) as CheckBox
        INX = root.findViewById(R.id.eq_INX) as CheckBox
        SPY = root.findViewById(R.id.eq_SPY) as CheckBox
        IVV = root.findViewById(R.id.eq_IVV) as CheckBox
        VTI = root.findViewById(R.id.eq_VTI) as CheckBox

        edit_back = root.findViewById(R.id.myportfolio_button_back_edit) as Button
        edit_change = root.findViewById(R.id.myportfolio_button_change) as Button
        edit_delete = root.findViewById(R.id.myportfolio_button_delete) as Button

        input_port_name.setText(name)

        RetrofitClient.instance.retrievePortfolio(id.toString()).enqueue(object :
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
                            BTC.isChecked = true
                        }
                        if(response.body()?.LTC != null){
                            LTC.isChecked = true
                        }
                        if(response.body()?.ETH != null){
                            ETH.isChecked = true
                        }
                        if(response.body()?.XAG != null){
                            XAG.isChecked = true
                        }
                        if(response.body()?.XAU != null){
                            XAU.isChecked = true
                        }
                        if(response.body()?.GOOGL != null){
                            GOOGL.isChecked = true
                        }
                        if(response.body()?.AAPL != null){
                            AAPL.isChecked = true
                        }
                        if(response.body()?.GM != null){
                            GM.isChecked = true
                        }
                        if(response.body()?.EUR != null){
                            EUR.isChecked = true
                        }
                        if(response.body()?.GBP != null){
                            GBP.isChecked = true
                        }
                        if(response.body()?.TRY != null){
                            TRY.isChecked = true
                        }
                        if(response.body()?.DJI != null){
                            DJI.isChecked = true
                        }
                        if(response.body()?.IXIC != null){
                            IXIC.isChecked = true
                        }
                        if(response.body()?.INX != null){
                            INX.isChecked = true
                        }
                        if(response.body()?.SPY != null){
                            SPY.isChecked = true
                        }
                        if(response.body()?.IVV != null){
                            IVV.isChecked = true
                        }
                        if(response.body()?.VTI != null){
                            VTI.isChecked = true
                        }

                    }
                }else{

                }
            }
            override fun onFailure(call: Call<OnePortfolioResponse>, t: Throwable) {

            }
        })

        edit_delete.setOnClickListener { root ->
            println("DELETE")
            deletePortfolio(root, id)
        }
        edit_change.setOnClickListener { root ->
            changePortfolio(root, id)
        }
        edit_back.setOnClickListener { root ->
            val parentActivityManager: FragmentManager =
                activity?.supportFragmentManager as FragmentManager
            removeFragment(parentActivityManager)
        }
        return root
    }

    fun changePortfolio(view: View, id: String) {
        if(input_port_name.text.isEmpty()){
            input_port_name.error = "Portfolio name cannot be empty."
            input_port_name.requestFocus()
            return
        }

        val edit_portfolio_obj = PortfolioEditRequestModel(input_port_name.text.toString(),
            BTC.isChecked, LTC.isChecked, ETH.isChecked,
            XAG.isChecked, XAU.isChecked, GOOGL.isChecked,
            AAPL.isChecked, GM.isChecked, EUR.isChecked,
            GBP.isChecked, TRY.isChecked, DJI.isChecked,
            IXIC.isChecked, INX.isChecked, SPY.isChecked,
            IVV.isChecked, VTI.isChecked)

        RetrofitClient.instance.updatePortfolio(id, edit_portfolio_obj).enqueue(object :
            Callback<PortfolioEditResponseModel> {
            override fun onResponse(
                call: Call<PortfolioEditResponseModel>,
                response: Response<PortfolioEditResponseModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("NOT CHANGED")
                    }else{
                        println("CHANGED")

                        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

                        fragmentTransaction(
                            parentActivityManager,
                            UserProfile.newInstance(),
                            (containerId!!.id),
                            true,
                            true,
                            false
                        )
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<PortfolioEditResponseModel>, t: Throwable) {

            }
        })

    }

    fun deletePortfolio(view: View, id: String) {
        println("DELETE1")
        RetrofitClient.instance.deletePortfolio(id).enqueue(object :
            Callback<PortfolioDeleteResponseModel> {
            override fun onResponse(
                call: Call<PortfolioDeleteResponseModel>,
                response: Response<PortfolioDeleteResponseModel>
            ) {
                println(response.toString())
                if(response.code() == 200 ){
                    if(response.body()?.detail != null){
                        println("DELETE2")
                    }else{
                        println("DELETE3")

                        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

                        fragmentTransaction(
                            parentActivityManager,
                            UserProfile.newInstance(),
                            (containerId!!.id),
                            true,
                            true,
                            false
                        )
                    }
                }else{

                }
            }
            override fun onFailure(call: Call<PortfolioDeleteResponseModel>, t: Throwable) {

            }
        })
        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        fragmentTransaction(
            parentActivityManager,
            UserProfile.newInstance(),
            (containerId!!.id),
            true,
            true,
            false
        )
    }

    companion object {
        fun newInstance(name: String?, id: String?): EditMyPortfolioFragment{
            val myportfolioFrag = EditMyPortfolioFragment()
            val args = Bundle()
            args.putSerializable("name", name)
            args.putSerializable("id", id)
            myportfolioFrag.arguments = args
            return myportfolioFrag
        }

    }

}


