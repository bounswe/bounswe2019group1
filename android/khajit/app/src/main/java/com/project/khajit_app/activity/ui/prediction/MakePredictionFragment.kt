package com.project.khajit_app.activity.ui.prediction

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager

import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.PredictionModel
import com.project.khajit_app.data.models.PredictionResponseModel
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakePredictionFragment : Fragment() , fragmentOperationsInterface {

    var containerId : ViewGroup? = null
    private lateinit var viewModel: MakePredictionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.make_prediction_fragment, container, false)
        containerId = container

        val equipmentName = root.findViewById(R.id.make_prediction_fragment_name) as TextView
        equipmentName.text = "BUY: " + arguments?.getString("equipment")

        val equipmentValue = root.findViewById(R.id.make_prediction_fragment_value) as TextView
        equipmentValue.text = "from: " + arguments?.getString("value")


        val increaseButton = root.findViewById(R.id.make_prediction_fragment_predict_increase) as Button
        val declineButton = root.findViewById(R.id.make_prediction_fragment_predict_decline) as Button
        val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager

        increaseButton.setOnClickListener {
            // send a request to the api to buy buyAmount.text amount of equipment.
            val predictionModel = PredictionModel(arguments?.getString("equipment")!!, true)
            println("predictionModel " + predictionModel.is_Rising + "  " + predictionModel.tradingEquipment)
            RetrofitClient.instance.makePrediction(predictionModel)
                .enqueue(object : Callback<PredictionResponseModel> {
                    override fun onResponse(
                        call: Call<PredictionResponseModel>?,
                        response: Response<PredictionResponseModel>?
                    ) {
                        print("response " + (response!!.code() == 200 ))
                        println("iki")
                        println("iki " + response!!.body()?.user)
                        println("iki " + response!!.body()?.tradingEquipment)
                        if(response!!.body()?.user != null) {
                            Toast.makeText(
                                context,
                                "PREDICTION: " + arguments?.getString("equipment") + " will increase!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Toast.makeText(
                                context,
                                "PREDICTION: " + arguments?.getString("equipment") + " predicted before, try later!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        removeFragment(parentActivityManager)


                    }

                    override fun onFailure(call: Call<PredictionResponseModel>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }

        declineButton.setOnClickListener {
            // send a request to the api to buy buyAmount.text amount of equipment.
            val predictionModel = PredictionModel(arguments?.getString("equipment")!!, false)
            RetrofitClient.instance.makePrediction(predictionModel)
                .enqueue(object : Callback<PredictionResponseModel> {
                    override fun onResponse(
                        call: Call<PredictionResponseModel>?,
                        response: Response<PredictionResponseModel>?
                    ) {
                        println("birrrr")
                        println("birrrr " + response!!.body()?.user)
                        println("birrrr " + response!!.body()?.tradingEquipment)
                        if(response!!.body()?.user != null) {
                            Toast.makeText(context,"PREDICTION: " + arguments?.getString("equipment")+ " will decline!", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(
                                context,
                                "PREDICTION: " + arguments?.getString("equipment") + " predicted before, try later!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        removeFragment(parentActivityManager)


                    }

                    override fun onFailure(call: Call<PredictionResponseModel>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                    }

                })
        }
        return root
    }




    companion object {
        fun newInstance(equipment : String, value : String): MakePredictionFragment {
            val fragmentEquipmentBuy = MakePredictionFragment()
            val args = Bundle()
            args.putSerializable("equipment",equipment)
            args.putSerializable("value",value)
            fragmentEquipmentBuy.arguments = args
            return fragmentEquipmentBuy
        }

    }

}
