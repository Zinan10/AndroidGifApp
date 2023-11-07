package com.gifs.app.user.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.gifs.app.user.databinding.TestItemBinding
import com.gifs.app.user.model.Data
import com.gifs.app.user.ui.adapter.viewholder.ItemViewHolder

class ItemAdapter : ListAdapter<Data, ItemViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            TestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
            oldItem == newItem
    }
}