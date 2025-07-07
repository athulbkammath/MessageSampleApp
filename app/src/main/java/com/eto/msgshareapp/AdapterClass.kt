package com.eto.msgshareapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val isListView:Boolean) :
    ListAdapter<TileDataClass, AdapterClass.ViewHolderClass>(TileDiffUtil()) {

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.tileImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvSubTitle: TextView = itemView.findViewById(R.id.tvSubTitle)
        val mrpPrice: TextView = itemView.findViewById(R.id.mrpPrice)
        val offerPrice: TextView = itemView.findViewById(R.id.offerPrice)
    }

    class TileDiffUtil : DiffUtil.ItemCallback<TileDataClass>() {
        override fun areItemsTheSame(oldItem: TileDataClass, newItem: TileDataClass): Boolean {
            return oldItem.tileId == newItem.tileId
        }

        override fun areContentsTheSame(oldItem: TileDataClass, newItem: TileDataClass): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = getItem(position)
        holder.rvImage.setImageResource(currentItem.tileImage)
        holder.tvTitle.text = currentItem.tileTitle
        holder.tvSubTitle.text = currentItem.tileSubTitle
        holder.mrpPrice.text = "Rs: ${currentItem.mrpPrice}/kg"
        holder.offerPrice.text = "Rs: ${currentItem.offerPrice}/Kg"
    }
    override fun getItemViewType(position: Int): Int {
        return if (isListView) R.layout.tile_layout else R.layout.grid_tile_layout
    }
}
