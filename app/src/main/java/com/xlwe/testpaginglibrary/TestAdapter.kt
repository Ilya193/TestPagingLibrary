package com.xlwe.testpaginglibrary

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.xlwe.testpaginglibrary.databinding.ItemBinding
import com.xlwe.testpaginglibrary.network.model.Result

class TestAdapter :
    PagingDataAdapter<Result, TestAdapter.ViewHolder>(
        comparator
    ) {
    class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val page = getItem(position)

        holder.binding.tvName.text = page?.name
        holder.binding.img.load(page?.image) {
            crossfade(500)
            transformations(CircleCropTransformation())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    companion object {
        private val comparator =
            object : DiffUtil.ItemCallback<Result>() {
                override fun areItemsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Result,
                    newItem: Result
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}