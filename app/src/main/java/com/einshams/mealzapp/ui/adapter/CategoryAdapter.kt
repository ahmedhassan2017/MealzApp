package com.einshams.mealzapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.einshams.domain.entity.Category
import com.einshams.mealzapp.R

class CategoryAdapter(
        private val onItemClick: (Category) -> Unit = {}
) : ListAdapter<Category, CategoryAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.titleText)
        private val descText: TextView = itemView.findViewById(R.id.descText)
        private val thumbImage: ImageView = itemView.findViewById(R.id.thumbImage)

        fun bind(item: Category) {
            titleText.text = item.category
            descText.text = item.categoryDescription

            // If you have Coil/Glide, load item.strCategoryThumb here.
            // For now we keep a placeholder to avoid dependency issues.

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
