package com.android.houseofindia.network.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoryResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("categoriesList")
    val categoryList: List<Category>? = null
) {
    @Keep
    data class Category(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("name")
        val name: String? = null,
        var isGrid: Boolean = false,
        var products: List<ProductResponse.ItemLists>? = null
    )
}