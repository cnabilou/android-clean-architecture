package com.smartservices.com.baseapp.browse

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.smartservices.com.baseapp.R
import com.smartservices.com.baseapp.model.MinionViewModel
import javax.inject.Inject

class BrowseAdapter @Inject constructor(): RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var minions: List<MinionViewModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.minion_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return minions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val minion = minions[position]
        holder.nameText.text = minion.name
        holder.titleText.text = minion.title

//        Glide.with(holder.itemView.context)
//            .load(minion.avatar)
//            .apply(RequestOptions().circleCrop())
//            .into(holder.avatarImage)
        holder.avatarImage.setImageResource(R.mipmap.ic_launcher_round)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView = view.findViewById(R.id.avatar)
        var nameText: TextView = view.findViewById(R.id.user_name)
        var titleText: TextView = view.findViewById(R.id.user_title)

    }
}