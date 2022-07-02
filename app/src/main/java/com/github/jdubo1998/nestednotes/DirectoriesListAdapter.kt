package com.github.jdubo1998.nestednotes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

private const val TAG = "DirectoriesListAdapter"

class DirectoriesListAdapter(private val context: Context, private val directory: Directory) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)
//    private var viewHolder: View? = null

    override fun getCount(): Int {
        Log.d(TAG, "getCount: ${directory.items.size}")
        return directory.items.size
    }

    override fun getItem(position: Int): Any {
        return directory.items[position]
    }

    override fun getItemId(position: Int): Long {
        return directory.items[position].hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {
            view = inflater.inflate(R.layout.item_nesteditem, parent, false)!!
            val holder:ViewHolder = ViewHolder.newInstance(view)
            view.tag = holder
        }

        val holder: ViewHolder = view.tag as ViewHolder

        if (directory.items[position] is Directory) {
            holder.iconImage.setImageResource(R.drawable.ic_icon_folder)
        } else if (directory.items[position] is Note) {
            holder.iconImage.setImageResource(R.drawable.ic_icon_note)
        }

        holder.titleText.text = directory.items[position].title

        return view
    }
}

private class ViewHolder {
    lateinit var iconImage: ImageView
    lateinit var titleText: TextView

    companion object {
        fun newInstance(view: View) = ViewHolder().apply {
            this.iconImage = view.findViewById<ImageView>(R.id.image_icon)!!
            this.titleText = view.findViewById<TextView>(R.id.text_title)!!
        }
    }
}