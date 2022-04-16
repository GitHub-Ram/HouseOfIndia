package com.android.houseofindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.houseofindia.MainActivity
import com.android.houseofindia.databinding.FragmentHotelMenuBinding
import com.android.houseofindia.network.models.CategoryResponse
import com.android.houseofindia.network.models.ProductResponse

class LinearGridMenuFragment(private val category: CategoryResponse.Category) :
    HotelMenuFragment<FragmentHotelMenuBinding>() {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotelMenuBinding {
        return FragmentHotelMenuBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = (requireActivity() as MainActivity).getProductViewModel()
        binding?.apply {
            tvCategory.text = category.name
            menuList.layoutManager = if (category.isGrid) GridLayoutManager(requireContext(), 2)
            else LinearLayoutManager(requireContext())
            loadMenu(category.id)
        }
    }

    override fun onMenuLoad(productResponse: ProductResponse?) {
        productResponse?.itemLists?.run {
            if (isNotEmpty()) binding?.menuList?.adapter =
                HotelMenuAdapter(this, category.isGrid)
        }
    }
}