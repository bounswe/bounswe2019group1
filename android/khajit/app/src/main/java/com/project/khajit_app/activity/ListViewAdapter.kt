package com.project.khajit_app.activity

import android.widget.BaseAdapter
import android.widget.TextView
import com.project.khajit_app.R
import android.view.ViewGroup
import android.view.View
import com.project.khajit_app.activity.ui.home.HomeFragment


// Adapter for prediction success ratios, NEVER CHANGE THIS FILE
class ListViewAdapter(
    internal var context: HomeFragment,
    internal var title: Array<String>,
    internal var description: Array<String>
) : BaseAdapter() {

    override fun getCount(): Int {
        return title.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    private inner class ViewHolder {
        internal var txtViewTitle: TextView? = null
        internal var txtViewDescription: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        val inflater = context.layoutInflater

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_listview, null)
            holder = ViewHolder()
            holder.txtViewTitle = convertView.findViewById(R.id.textView1) as TextView
            holder.txtViewDescription = convertView.findViewById(R.id.textView2) as TextView
            convertView!!.tag = holder
        } else {
            holder = convertView!!.tag as ViewHolder
        }

        holder.txtViewTitle!!.text = title[position]
        holder.txtViewDescription!!.text = description[position]

        return convertView
    }

}