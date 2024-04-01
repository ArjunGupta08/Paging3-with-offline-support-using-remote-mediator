package com.arjungupta08.paging3offlinesupport.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arjungupta08.paging3offlinesupport.R

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loader, parent, false)
        return LoaderViewHolder(view)
    }

    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val loader = itemView.findViewById<ProgressBar>(R.id.progressBar)
        fun bind (loadState: LoadState) {
            loader.isVisible = loadState is LoadState.Loading
        }
    }

}