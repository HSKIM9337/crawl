package com.example.crawl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crawl.databinding.ItemListBinding

class MyListAdapter : ListAdapter<Data, MyListAdapter.ViewHolder>(myListCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.ViewHolder {
            val viewHolder = ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        return viewHolder
    }

    override fun onBindViewHolder(holder: MyListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            with(binding) {
                tvNum.text = item.num
                tvTitle.text = item.title
            }

        }
    }

    companion object {
        private val myListCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.hashCode() === newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}