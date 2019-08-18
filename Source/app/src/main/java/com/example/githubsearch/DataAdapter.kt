/**
 * @author: Borislav Milijic
 */

package com.example.githubsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myhexaville.simplerecyclerview.SimpleRecyclerView
import com.squareup.picasso.Picasso

class DataAdapter (list: MutableList<RepoItems>, onRepoListener: OnRepoListener)
    : SimpleRecyclerView.Adapter<DataAdapter.Companion.ViewHolder>() {

    private var result: MutableList<RepoItems> = mutableListOf()
    private var mOnRepoListener: OnRepoListener

    init {
        //initialise Data
        setData(list)
        mOnRepoListener = onRepoListener
    }

    //passing the Data fetched from API to RecyclerView Adapter
    fun setData (data: List<RepoItems>) {
        result.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateHolder(parent: ViewGroup?): ViewHolder {
        //inflates a custom Row Layout to be shown in RecyclerView
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.repo_row_layout, parent, false)
        return ViewHolder(v, mOnRepoListener)    }

    override fun onBindHolder(holder: ViewHolder?, position: Int) {
        //binds the data from API to respected views
        holder?.repo_name?.text   = result[position].name
        holder?.repo_description?.text   = result[position].description
        holder?.repo_owner?.text   = result[position].owner.login
        //loads img url from JSON
        Picasso
            .get()
            .load(result[position].owner.avatar_url)
            .into(holder?.avatar)}

    override fun getCount(): Int {
        return result.size
    }

    //using companion object since it is safer than inner class
    companion object {
        //ViewHolder to initialize the Views in CustomRow
        class ViewHolder(itemView: View, onRepoListener: OnRepoListener
        ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
            override fun onClick(p0: View?) {
                mOnRepoListener.onRepoClick(adapterPosition)
            }

            var repo_name: TextView
            var repo_owner: TextView
            var repo_description: TextView
            var avatar: ImageView
            var mOnRepoListener: OnRepoListener

            init {
                repo_name = itemView.findViewById(R.id.repo_name)
                repo_owner = itemView.findViewById(R.id.repo_owner)
                repo_description = itemView.findViewById(R.id.repo_description)
                avatar = itemView.findViewById(R.id.avatar)
                this.mOnRepoListener = onRepoListener

                itemView.setOnClickListener(this)
            }
        }
    }
    //interface for ClickableItems from RecycleView
    interface OnRepoListener {
        fun onRepoClick(position: Int)
    }
}