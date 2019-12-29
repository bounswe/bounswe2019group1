package com.project.khajit_app.activity.ui.event

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.project.khajit_app.R
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.EventModel
import com.project.khajit_app.data.models.ListEventModel
import com.project.khajit_app.databinding.DisplayEventRecyclerviewItemModelBinding
import interfaces.fragmentOperationsInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListEventFragment : Fragment() , fragmentOperationsInterface {

    private var titles = ArrayList<String>()
    private var countries = ArrayList<String>()
    private var dates = ArrayList<String>()
    private var impacts = ArrayList<String>()
    private var forecasts = ArrayList<String>()
    private var previous_values = ArrayList<String>()

    private var containerId : ViewGroup? = null
    private lateinit var recyclerView : RecyclerView


    companion object {
        fun newInstance(): ListEventFragment {
            val args = Bundle()
            val fragment = ListEventFragment()
            fragment.arguments = args
            return fragment
        }
    }


    private lateinit var viewModel: ListEventViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)

        RetrofitClient.instance.getAllEvents().enqueue(object :
            Callback<ListEventModel> {
            override fun onResponse(
                call: Call<ListEventModel>,
                response: Response<ListEventModel>
            ) {
                println(response.toString())
                print("response " + (response.code() == 200 ))
                if(response.code() == 200 ){
                    print("burdayız")
                    var count = response.body()?.count
                    countries.clear()
                    titles.clear()
                    dates.clear()
                    impacts.clear()
                    forecasts.clear()
                    previous_values.clear()

                    var results = response.body()?.results as List<EventModel>
                    print(count)

                    for (event in results) {
                        titles.add(event.title as String)
                        countries.add(event.country as String)
                        dates.add(event.date as String)
                        impacts.add(event.impact as String)
                        forecasts.add(event.forecast as String)
                        previous_values.add(event.previous as String)


                    }
                    recyclerView.layoutManager = GridLayoutManager(activity, 1)
                    recyclerView.adapter = EventListAdapter(titles,countries,dates,impacts,forecasts,previous_values,context)

                }else{
                    print("nalaka")
                    Log.d("error message:", response.message())
                }
            }
            override fun onFailure(call: Call<ListEventModel>, t: Throwable) {
                println(t.message)
                println(t)
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
            }
        })


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.list_event_fragment, container, false)

        val view: View = inflater.inflate(R.layout.list_event_fragment, container,
            false)
        val activity = activity as Context
        containerId = container
        recyclerView = view.findViewById(R.id.list_event_fragment_recyclerview) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 1)
        recyclerView.adapter = EventListAdapter(titles,countries,dates,impacts,forecasts,previous_values,activity)


        return view
    }

    internal inner class EventListAdapter(
        val titles : ArrayList<String>,
        val countries : ArrayList<String>,
        val dates : ArrayList<String>,
        val impacts : ArrayList<String>,
        val forecasts : ArrayList<String>,
        val previous_values : ArrayList<String>,
        val context: Context) :RecyclerView.Adapter<ViewHolder>(){

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val DisplayEventRecyclerviewItemBinding =
                DisplayEventRecyclerviewItemModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(DisplayEventRecyclerviewItemBinding.root, DisplayEventRecyclerviewItemBinding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            if (titles[position].equals(""))
                titles[position] = "-"
            if (countries[position].equals(""))
                countries[position] = "-"
            if (dates[position].equals(""))
                dates[position] = "-"
            if (impacts[position].equals(""))
                impacts[position] = "-"
            if (forecasts[position].equals(""))
                forecasts[position] = "-"
            if (previous_values[position].equals(""))
                previous_values[position] = "-"


            //countries[position] = "Country : " + countries[position]
            //dates[position] = "Date : " + dates[position]
            //impacts[position] = "Impact : " + impacts[position]
            //forecasts[position] = "Forecast : " + forecasts[position]
            //previous_values[position] = "Previous : " + previous_values[position]

            val event = EventModel(titles[position], "Country : " + countries[position],
                "Date : " + dates[position],"Impact : " + impacts[position],"Forecast : " + forecasts[position],"Previous : " + previous_values[position])
            viewHolder.setData(event)
            //viewHolder.itemView.setOnClickListener { listener.onArticleSelected(article,isGuest,isLoggedInUser,isFeedPage,isFollowing,userId) }
            viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val parentActivityManager : FragmentManager = activity?.supportFragmentManager as FragmentManager


                    fragmentTransaction(
                        parentActivityManager,
                        DisplayEventModel.newInstance(event),
                        (containerId!!.id),
                        true,
                        true,
                        false)
                }
            })
        }

        override fun getItemCount() = titles.size
    }



    internal inner class ViewHolder constructor(itemView: View,
                                                private val displayEventListBinding:
                                                DisplayEventRecyclerviewItemModelBinding

    ) :
        RecyclerView.ViewHolder(itemView) {
        fun setData(eventModel: EventModel) {
            print("##### SET DATA İÇİ " + eventModel.title)
            displayEventListBinding.eventModel = eventModel
        }
    }

}
