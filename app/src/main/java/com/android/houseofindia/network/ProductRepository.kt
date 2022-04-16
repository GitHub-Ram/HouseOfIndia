package com.android.houseofindia.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepository(private val apiInterface: ApiInterface) : ProductDataSource {

    override suspend fun getCategories() = flow {
        emit(apiInterface.getCategories())
    }.flowOn(Dispatchers.IO)

    override suspend fun getHomeData() = flow {
        emit(apiInterface.getHomeData())
    }.flowOn(Dispatchers.IO)

    override suspend fun getProducts(id: String) = flow {
        emit(apiInterface.getProducts(id))
    }.flowOn(Dispatchers.IO)
}