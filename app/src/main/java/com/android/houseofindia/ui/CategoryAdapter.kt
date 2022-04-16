package com.android.houseofindia.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.houseofindia.databinding.ListItemCategoryBinding
import com.android.houseofindia.network.models.CategoryResponse

class CategoryAdapter(private val categories: List<CategoryResponse.Category>,private val textSizes: List<Float>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ListItemCategoryBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder.binding) {
            tvCategory.text = categories[position].name
            categories[position].products?.run {
                if(categories[position].isGrid)
                    rvProducts.layoutManager = GridLayoutManager(rvProducts.context, 2)
                else rvProducts.layoutManager = LinearLayoutManager(rvProducts.context)
                rvProducts.adapter = HotelMenuAdapter(this, categories[position].isGrid, textSizes)
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    inner class CategoryViewHolder(val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}