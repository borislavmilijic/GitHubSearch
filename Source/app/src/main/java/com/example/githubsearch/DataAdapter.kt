package com.example.githubsearch

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DataAdapter (val context: Context): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var result: List<RepoItems> = listOf()

    fun setData (data: List<RepoItems>) {
        var size = data.size
        this.result = data
        var sizeNew = data.size
        notifyDataSetChanged()
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repo_name.text = result[position].name
        holder.repo_description.text = result[position].description
        holder.repo_owner.text = result[position].owner.login

        //GLIDE Lib
        holder.avatar.setImageURI(Uri.parse(result[position].owner.avatar_url))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repo_row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val repo_name: TextView = itemView.findViewById(R.id.repo_name)
        val repo_owner: TextView = itemView.findViewById(R.id.repo_owner)
        val repo_description: TextView = itemView.findViewById(R.id.repo_description)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
    }
}