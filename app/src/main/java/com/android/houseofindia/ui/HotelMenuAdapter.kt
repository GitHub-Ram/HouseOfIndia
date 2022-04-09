package com.android.houseofindia.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.houseofindia.MainActivity
import com.android.houseofindia.databinding.ListItemHotelMenuBinding
import com.android.houseofindia.databinding.ListItemHotelMenuGridBinding
import com.android.houseofindia.network.models.ProductResponse

class HotelMenuAdapter(
    private val products: List<ProductResponse.ItemLists>,
    private val isGrid: Boolean
) : RecyclerView.Adapter<HotelMenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        if (viewType == 2) return MenuViewHolder(
            ListItemHotelMenuGridBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
        return MenuViewHolder(
            ListItemHotelMenuBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        with(holder.binding) {
            when (this) {
                is ListItemHotelMenuGridBinding -> {
                    space.visibility = if (position % 2 == 0) View.VISIBLE else View.GONE
                    tvProduct.text = products[position].productName?.trim()
                    ivVeg.visibility = if (products[position].productType == 1) View.VISIBLE
                    else View.GONE
                    tvPrice.text = products[position].productPrice?.toInt().toString()
                    tvProductDescription.text = products[position].productDescription?.trim()
                    tvProductDescription.visibility =
                        if (products[position].productDescription.isNullOrEmpty())
                            View.GONE else View.VISIBLE
                    holder.itemView.setOnClickListener {
                        products[position].productImage?.run { openImage(it.context, this) }
                    }
                }
                is ListItemHotelMenuBinding -> {
                    tvProduct.text = products[position].productName?.trim()
                    ivVeg.visibility = if (products[position].productType == 1) View.VISIBLE
                    else View.GONE
                    tvPrice.text = products[position].productPrice?.toInt().toString()
                    tvProductDescription.text = products[position].productDescription?.trim()
                    tvProductDescription.visibility =
                        if (products[position].productDescription.isNullOrEmpty())
                            View.GONE else View.VISIBLE
                    holder.itemView.setOnClickListener {
                        products[position].productImage?.run { openImage(it.context, this) }
                    }
                }
                else -> {}
            }
        }
    }

    private fun openImage(context: Context, imageName: String) {
        ImageViewerFragment(imageName).show(
            (context as MainActivity).supportFragmentManager,
            "FoodImage"
        )
    }

    override fun getItemCount(): Int = products.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return if (isGrid) 2 else 1
    }

    inner class MenuViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}