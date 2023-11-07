package com.gifs.app.user.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gifs.app.user.R
import com.gifs.app.user.databinding.TestItemBinding
import com.gifs.app.user.model.Data

class ItemViewHolder(
    private val binding: TestItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Data) {
        binding.apply {
            Glide.with(root.context)
                .asGif()
                .load(item.images.original.url)
                .placeholder(R.drawable.loading)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivItem)
        }
    }
}