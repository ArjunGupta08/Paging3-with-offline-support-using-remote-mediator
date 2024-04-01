package com.arjungupta08.paging3offlinesupport.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arjungupta08.paging3.model.Result
import com.arjungupta08.paging3offlinesupport.R

class QuotePagingAdapter : PagingDataAdapter<Result, QuotePagingAdapter.ViewHolder>(COMPARATOR) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text1 = itemView.findViewById<TextView>(R.id.t1)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<com.arjungupta08.paging3.model.Result>() {
            override fun areItemsTheSame(oldItem: com.arjungupta08.paging3.model.Result, newItem: com.arjungupta08.paging3.model.Result): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: com.arjungupta08.paging3.model.Result, newItem: com.arjungupta08.paging3.model.Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.text1.text = data?.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

}