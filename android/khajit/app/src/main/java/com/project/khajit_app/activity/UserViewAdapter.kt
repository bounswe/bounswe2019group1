package com.project.khajit_app.activity

import android.widget.BaseAdapter
import android.widget.TextView
import com.project.khajit_app.R
import android.view.ViewGroup
import android.view.View
import com.project.khajit_app.activity.ui.home.HomeFragment
import com.project.khajit_app.activity.ui.search.SearchFragment


// Adapter for prediction success ratios, NEVER CHANGE THIS FILE
class UserViewAdapter(
    internal var context: SearchFragment,
    internal var username: ArrayList<String>,
    internal var title: ArrayList<String>
) : BaseAdapter() {

    override fun getCount(): Int {
        return username.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    private inner class ViewHolder {
        internal var txtViewUsername: TextView? = null
        internal var txtViewTitle: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        val inflater = context.layoutInflater

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_user, null)
            holder = ViewHolder()
            holder.txtViewUsername = convertView.findViewById(R.id.username) as TextView
            holder.txtViewTitle = convertView.findViewById(R.id.title) as TextView
            convertView!!.tag = holder
        } else {
            holder = convertView!!.tag as ViewHolder
        }

        holder.txtViewUsername!!.text = username[position]
        holder.txtViewTitle!!.text = title[position]

        return convertView
    }

}