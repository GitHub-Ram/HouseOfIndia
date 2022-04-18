package com.android.houseofindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.houseofindia.MainActivity
import com.android.houseofindia.databinding.FragmentMultipleMenuBinding
import com.android.houseofindia.network.models.CategoryResponse
import com.android.houseofindia.network.models.ProductResponse

class MultipleMenuFragment(private val categories: List<CategoryResponse.Category>,private val textSizes: List<Float>,private val backImageResId : Int) :
    HotelMenuFragment<FragmentMultipleMenuBinding>() {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMultipleMenuBinding {
        return FragmentMultipleMenuBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = (requireActivity() as MainActivity).getProductViewModel()
        binding?.apply {
            backImage.setImageResource(backImageResId)
            categories.forEach {
                it.products = productViewModel?.productsMap?.get(it.id)?.itemLists
            }
            rvCategory.adapter = CategoryAdapter(categories,textSizes)
        }
    }

    override fun onMenuLoad(productResponse: ProductResponse?) {

    }
}