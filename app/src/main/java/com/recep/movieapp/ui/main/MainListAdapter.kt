package com.recep.movieapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.recep.movieapp.databinding.MainListItemBinding
import com.recep.movieapp.model.api.Result

class MainListAdapter(
    private val items: List<Result>?,
    private val clickItem: (Result?) -> Unit
) : RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainListViewHolder(MainListItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bind(items?.get(position), clickItem)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class MainListViewHolder(private val binding: MainListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result?, clickItem: (Result?) -> Unit) {
            binding.item = item
            binding.rootView.setOnClickListener { clickItem.invoke(item) }
            binding.executePendingBindings()
        }
    }
}