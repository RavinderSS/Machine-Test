package com.ravinder.machinetest.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ravinder.machinetest.data.local.MarkerEntity
import com.ravinder.machinetest.databinding.ListItemBinding

class MarkerAdapter(
    private val onDeleteClick: (MarkerEntity) -> Unit
) : ListAdapter<MarkerEntity, MarkerAdapter.MarkerViewHolder>(MarkerDiffCallback()) {

    inner class MarkerViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marker: MarkerEntity) {
            binding.tvLat.text = String.format("LAT %.3f", marker.latitude)
            binding.tvLong.text = String.format("LNG %.3f", marker.longitude)
            binding.ivDelete.setOnClickListener {
                onDeleteClick(marker)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarkerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MarkerDiffCallback : DiffUtil.ItemCallback<MarkerEntity>() {
        override fun areItemsTheSame(oldItem: MarkerEntity, newItem: MarkerEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarkerEntity, newItem: MarkerEntity): Boolean {
            return oldItem == newItem
        }
    }
}
