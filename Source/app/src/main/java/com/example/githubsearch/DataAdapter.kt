package com.example.githubsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DataAdapter(
    list: MutableList<RepoItems> ) : RecyclerView.Adapter<DataAdapter.Companion.ViewHolder>() {

    private var result: MutableList<RepoItems> = mutableListOf()

    init {
        setData(list)
    }

    fun setData (data: List<RepoItems>) {
        result.addAll(data)
        val init = data.size
        notifyItemRangeChanged(init, result.size)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // val repo_url = result[position].url

        holder.repo_name.text = result[position].name
        holder.repo_description.text = result[position].description
        holder.repo_owner.text = result[position].owner.login
        Picasso
            .get()
            .load(result[position].owner.avatar_url)
            .into(holder.avatar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repo_row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var repo_name: TextView
            var repo_owner: TextView
            var repo_description: TextView
            var avatar: ImageView

            init {
                repo_name = itemView.findViewById(R.id.repo_name)
                repo_owner = itemView.findViewById(R.id.repo_owner)
                repo_description = itemView.findViewById(R.id.repo_description)
                avatar = itemView.findViewById(R.id.avatar)
            }
        }
    }
}