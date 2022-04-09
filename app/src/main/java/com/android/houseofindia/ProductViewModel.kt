package com.android.houseofindia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.houseofindia.network.ProductDataSource
import com.android.houseofindia.network.models.CategoryResponse
import com.android.houseofindia.network.models.ProductResponse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

class ProductViewModel : ViewModel() {
    lateinit var productRepo: ProductDataSource
    private lateinit var categoryResponse: MutableLiveData<CategoryResponse?>
    private lateinit var productResponse: MutableLiveData<ProductResponse?>
    var categories: CategoryResponse? = null
    var productsMap = ConcurrentHashMap<String, ProductResponse?>()

    fun getCategories(): LiveData<CategoryResponse?> {
        categoryResponse = MutableLiveData()
        viewModelScope.launch {
            productRepo.getCategories().catch {
                categoryResponse.value = null
            }.collect {
                val response = if (it.code() == 200) it.body() else null
                categories = response
                categoryResponse.value = response
            }
        }
        return categoryResponse
    }

    fun fetchAllProducts(categoryList: List<CategoryResponse.Category>? = categories?.categoryList) {
        categoryList?.forEach { category ->
            category.id?.let { id ->
                if (!productsMap.contains(id)) {
                    viewModelScope.launch {
                        productRepo.getProducts(id).collect {
                            val response = if (it.code() == 200) it.body() else null
                            productsMap[id] = response
                        }
                    }
                }
            }
        }
    }

    fun getProducts(id: String): LiveData<ProductResponse?> {
        productResponse = MutableLiveData()
        viewModelScope.launch {
            productRepo.getProducts(id).catch {
                productResponse.value = null
            }.collect {
                val response = if (it.code() == 200) it.body() else null
                productsMap[id] = response
                productResponse.value = response
            }
        }
        return productResponse
    }
}