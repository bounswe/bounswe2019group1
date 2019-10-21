package com.project.khajit_app.activity.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.khajit_app.R
import com.project.khajit_app.activity.EditingProfilePageActivity
import com.project.khajit_app.activity.ListViewAdapter
import com.project.khajit_app.api.RetrofitClient
import com.project.khajit_app.data.models.BasicRegisterResponse
import com.project.khajit_app.data.models.BasicUser
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var equipments = arrayOf(
        "Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Max OS X", "Max OS X")
    var rates = arrayOf(
        "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73", "%73")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val bio_tex = root.findViewById(R.id.text_bio) as TextView
        bio_tex.movementMethod = ScrollingMovementMethod()

        // This will be used for further methods in order to set prediction rates
        var lview =  root.findViewById(R.id.list_prediction_name) as ListView
        var ladapter = ListViewAdapter(this, equipments, rates)
        lview.adapter = ladapter

        var b =  root.findViewById(R.id.button_about) as Button
        b.setOnClickListener(goEdit)

        var loader = root.findViewById(R.id.progress_loader) as ProgressBar
        loader.visibility = View.GONE


        Toast.makeText(this.context, "IM AT HOME", Toast.LENGTH_LONG).show()
        /*
        var listview = root.findViewById(R.id.list_prediction_name) as ListView
        listview.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this.context, "text is " + " $position", Toast.LENGTH_LONG).show()
            var ite = ladapter.getItem(position) as String
        }
        */

        return root
    }

    private val goEdit = View.OnClickListener { view ->
        startActivity(Intent(activity, EditingProfilePageActivity::class.java))
    }

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentHome = HomeFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

}