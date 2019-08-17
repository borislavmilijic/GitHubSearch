package com.example.githubsearch

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text


class DataAdapter : RecyclerView.Adapter<DataAdapter.Companion.ViewHolder> {

    private var result: List<RepoItems> = listOf()
    lateinit var context: Context
    lateinit var list: List<RepoItems>

    constructor(list: List<RepoItems>) :super() {
        this.list = list
    }


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
        //holder.avatar.setImageURI(Uri.parse(result[position].owner.avatar_url))
        Glide.with(context).load(result[position].owner.avatar_url).asBitmap().into(holder.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repo_row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    companion object {
        class ViewHolder : RecyclerView.ViewHolder {

            lateinit var repo_name: TextView
            lateinit var repo_owner: TextView
            lateinit var repo_description: TextView
            lateinit var avatar: ImageView

            constructor(itemView: View) : super(itemView) {
                repo_name = itemView!!.findViewById(R.id.repo_name)
                repo_owner = itemView!!.findViewById(R.id.repo_owner)
                repo_description = itemView!!.findViewById(R.id.repo_description)
                avatar = itemView!!.findViewById(R.id.avatar)
            }
        }
    }
}