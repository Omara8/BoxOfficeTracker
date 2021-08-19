package com.planatech.bo.tracker.comingsoon.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.planatech.bo.tracker.comingsoon.model.UpcomingResults
import com.planatech.bo.tracker.databinding.UpcomingItemLayoutBinding

class ComingSoonAdapter(private val itemClickCallback: (item: UpcomingResults) -> Unit) :
    PagingDataAdapter<UpcomingResults, ComingSoonAdapter.SearchItemsViewHolder>(diffCallBack) {

    class SearchItemsViewHolder(val binding: UpcomingItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: SearchItemsViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding.item = item
            holder.itemView.setOnClickListener {
                itemClickCallback(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UpcomingItemLayoutBinding.inflate(layoutInflater, parent, false)
        return SearchItemsViewHolder(binding)
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<UpcomingResults>() {
            override fun areItemsTheSame(oldItem: UpcomingResults, newItem: UpcomingResults): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UpcomingResults, newItem: UpcomingResults): Boolean =
                oldItem == newItem
        }
    }

}