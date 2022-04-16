package com.android.houseofindia.ui

import android.util.TypedValue
import androidx.viewbinding.ViewBinding
import com.android.houseofindia.ProductViewModel
import com.android.houseofindia.R
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.network.models.ProductResponse

abstract class HotelMenuFragment<VB : ViewBinding> : BaseFragment<Nothing, VB>() {
    protected var productViewModel: ProductViewModel? = null

    override val viewModel = null

    protected fun getProductsByCategoryId(categoryId: String): ProductResponse? {
        return if (productViewModel?.productsMap?.containsKey(categoryId) == true)
            productViewModel?.productsMap?.get(categoryId)
        else null
    }

    protected fun loadMenu(categoryId: String?) {
        if (!categoryId.isNullOrEmpty()) {
            if (productViewModel?.productsMap?.containsKey(categoryId) == false) {
                // TODO: Show Progress Bar
                productViewModel?.getProducts(categoryId)?.observe(viewLifecycleOwner) {
                    // TODO: Hide Progress Bar
                    onMenuLoad(it)
                }
            } else onMenuLoad(productViewModel?.productsMap?.get(categoryId))
        }
    }

    protected abstract fun onMenuLoad(productResponse: ProductResponse?)
}