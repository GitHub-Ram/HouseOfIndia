package com.android.houseofindia.network

import com.android.houseofindia.network.models.CategoryResponse
import com.android.houseofindia.network.models.HomeDataResponse
import com.android.houseofindia.network.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("getcategory")
    suspend fun getCategories(): Response<CategoryResponse?>

    @GET("getproduct")
    suspend fun getProducts(@Query("category_id") id: String): Response<ProductResponse?>

    @GET("gethomedata")
    suspend fun getHomeData(): Response<HomeDataResponse?>
}