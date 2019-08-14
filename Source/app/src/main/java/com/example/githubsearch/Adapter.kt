package com.example.githubsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/*

class AdapterString : ListAdapter<String, AdapterString.ItemViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder (
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun OnBindViewHolder(holder: AdapterString.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind (item: String) = with(itemView) {
            itemView.textView.text = item
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}


 */