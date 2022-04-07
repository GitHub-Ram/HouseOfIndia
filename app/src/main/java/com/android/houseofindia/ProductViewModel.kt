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

class ProductViewModel(private val productRepo: ProductDataSource): ViewModel() {
    private lateinit var categoryResponse: MutableLiveData<CategoryResponse?>
    private lateinit var productResponse: MutableLiveData<ProductResponse?>

    fun getCategories(): LiveData<CategoryResponse?> {
        categoryResponse = MutableLiveData()
        viewModelScope.launch {
            productRepo.getCategories().catch {
                categoryResponse.value = null
            }.collect {
                categoryResponse.value = if(it.code() == 200) it.body() else null
            }
        }
        return categoryResponse
    }

    fun getProducts(id: String): LiveData<ProductResponse?> {
        productResponse = MutableLiveData()
        viewModelScope.launch {
            productRepo.getProducts(id).catch {
                productResponse.value = null
            }.collect {
                productResponse.value = if(it.code() == 200) it.body() else null
            }
        }
        return productResponse
    }
}