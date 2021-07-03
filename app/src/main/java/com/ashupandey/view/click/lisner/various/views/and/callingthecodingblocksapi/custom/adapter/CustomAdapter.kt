package com.ashupandey.view.click.lisner.various.views.and.callingthecodingblocksapi.custom.adapter

import Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashupandey.view.click.lisner.various.views.and.callingthecodingblocksapi.R
import kotlinx.android.synthetic.main.layout_recycler.view.*

class CustomAdapter(val list : MutableList<Data>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {


   class  CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_recycler,
            parent,
            false
        )

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemView.idUser.text = "Id: " + list[position].id.toString()
        holder.itemView.name.text =   "Name: " + list[position].first_name + " " + list[position].last_name
        holder.itemView.email.text =  "Email: " + list[position].email
    }

    override fun getItemCount(): Int {
       return list.size
    }

}