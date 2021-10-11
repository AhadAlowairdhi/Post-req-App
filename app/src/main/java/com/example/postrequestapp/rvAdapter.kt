package com.example.postrequestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class rvAdapter(private val list: ArrayList<User>) : RecyclerView.Adapter<rvAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row
                , parent
                , false
            )
        )
           }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = list[position]

        holder.itemView.apply {
            tv2.text = user.name
            tv3.text = user.location
        }
    }

    override fun getItemCount(): Int = list.size
}