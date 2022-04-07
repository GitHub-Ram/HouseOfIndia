package com.android.houseofindia.network

import com.android.houseofindia.network.models.CategoryResponse
import com.android.houseofindia.network.models.ProductResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductDataSource {

    suspend fun getCategories(): Flow<Response<CategoryResponse?>>

    suspend fun getProducts(id: String): Flow<Response<ProductResponse?>>
}