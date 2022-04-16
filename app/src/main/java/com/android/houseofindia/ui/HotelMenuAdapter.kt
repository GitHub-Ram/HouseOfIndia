package com.android.houseofindia.ui

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.android.houseofindia.MainActivity
import com.android.houseofindia.databinding.ListItemHotelMenuBinding
import com.android.houseofindia.databinding.ListItemHotelMenuGridBinding
import com.android.houseofindia.network.models.ProductResponse

class HotelMenuAdapter(
    private val products: List<ProductResponse.ItemLists>, private val isGrid: Boolean,
    private val isMultiple: Boolean = false
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
                    if (isMultiple) {
                        if (position % 2 == 1) spaceStart.visibility = View.VISIBLE
                        else spaceStart.visibility = View.GONE
                    } else spaceStart.visibility = View.VISIBLE
                    spaceEnd.visibility =
                        if (isMultiple) View.GONE else if (position % 2 == 1) View.VISIBLE else View.GONE
                    tvProduct.text = products[position].productName?.trim()
                    tvProduct.adjustTextSize()
                    ivVeg.visibility = if (products[position].productType == 1) View.VISIBLE
                    else View.GONE
                    ivVeg.adjustSize()
                    tvPrice.text = products[position].productPrice?.toInt().toString()
                    tvPrice.adjustTextSize()
                    tvProductDescription.text = products[position].productDescription?.trim()
                    tvProductDescription.adjustTextSize(true)
                    tvProductDescription.visibility =
                        if (products[position].productDescription.isNullOrEmpty())
                            View.GONE else View.VISIBLE
                    holder.itemView.setOnClickListener {
                        products[position].productImage?.run { openImage(it.context, this) }
                    }
                }
                is ListItemHotelMenuBinding -> {
                    tvProduct.text = products[position].productName?.trim()
                    tvProduct.adjustTextSize()
                    ivVeg.visibility = if (products[position].productType == 1) View.VISIBLE
                    else View.GONE
                    ivVeg.adjustSize()
                    tvPrice.text = products[position].productPrice?.toInt().toString()
                    tvPrice.adjustTextSize()
                    tvProductDescription.text = products[position].productDescription?.trim()
                    tvProductDescription.adjustTextSize(true)
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

    private fun TextView.adjustTextSize(isDescription: Boolean = false) {
        if (isGrid) {
            if (isDescription) {
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX, if (isMultiple || products.size > 14)
                        context.resources.getDimension(com.intuit.ssp.R.dimen._4ssp)
                    else context.resources.getDimension(com.intuit.ssp.R.dimen._5ssp)
                )
            } else {
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX, if (isMultiple || products.size > 14)
                        context.resources.getDimension(com.intuit.ssp.R.dimen._5ssp)
                    else context.resources.getDimension(com.intuit.ssp.R.dimen._9ssp)
                )
            }
        } else {
            if (isDescription) {
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX, if (isMultiple)
                        context.resources.getDimension(com.intuit.ssp.R.dimen._5ssp)
                    else context.resources.getDimension(com.intuit.ssp.R.dimen._6ssp)
                )
            } else {
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX, if (isMultiple)
                        context.resources.getDimension(com.intuit.ssp.R.dimen._7ssp)
                    else context.resources.getDimension(com.intuit.ssp.R.dimen._9ssp)
                )
            }
        }
    }

    private fun ImageView.adjustSize() {
        requestLayout()
        val layoutParameters = layoutParams
        if (isGrid) {
            if (isMultiple || products.size > 14) {
                layoutParameters.width =
                    context.resources.getDimension(com.intuit.sdp.R.dimen._6sdp).toInt()
                layoutParameters.height =
                    context.resources.getDimension(com.intuit.sdp.R.dimen._6sdp).toInt()
            }
        } else {
            if (isMultiple) {
                layoutParameters.width =
                    context.resources.getDimension(com.intuit.sdp.R.dimen._10sdp).toInt()
                layoutParameters.height =
                    context.resources.getDimension(com.intuit.sdp.R.dimen._10sdp).toInt()
            } else {
                layoutParameters.width =
                    context.resources.getDimension(com.intuit.sdp.R.dimen._12sdp).toInt()
                layoutParameters.height =
                    context.resources.getDimension(com.intuit.sdp.R.dimen._12sdp).toInt()
            }
        }
        layoutParams = layoutParameters
    }

    override fun getItemCount(): Int = products.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return if (isGrid) 2 else 1
    }

    inner class MenuViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}