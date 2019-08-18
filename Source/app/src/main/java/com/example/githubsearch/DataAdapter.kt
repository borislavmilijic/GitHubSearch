package com.example.githubsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myhexaville.simplerecyclerview.SimpleRecyclerView
import com.squareup.picasso.Picasso

class DataAdapter (list: MutableList<RepoItems>,
                            onRepoListener: OnRepoListener) : SimpleRecyclerView.Adapter<DataAdapter.Companion.ViewHolder>() {
    override fun onCreateHolder(parent: ViewGroup?): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.repo_row_layout, parent, false)
        return ViewHolder(v, mOnRepoListener)    }

    override fun onBindHolder(holder: ViewHolder?, position: Int) {
        holder?.repo_name?.text   = result[position].name
        holder?.repo_description?.text   = result[position].description
        holder?.repo_owner?.text   = result[position].owner.login
        Picasso
            .get()
            .load(result[position].owner.avatar_url)
            .into(holder?.avatar)}

    override fun getCount(): Int {
        return result.size
    }

    private var result: MutableList<RepoItems> = mutableListOf()
    private var mOnRepoListener: OnRepoListener

    init {
        setData(list)
        mOnRepoListener = onRepoListener
    }

    fun setData (data: List<RepoItems>) {
        result.addAll(data)
        val init = data.size
        //notifyItemRangeChanged(init, result.size)
        notifyDataSetChanged()
    }

    companion object {
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

    interface OnRepoListener {
        fun onRepoClick(position: Int)
    }
}