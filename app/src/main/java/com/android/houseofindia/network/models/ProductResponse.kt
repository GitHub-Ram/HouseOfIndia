package com.android.houseofindia.network.models

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("categoryDetails")
    val categoryDetails: CategoryDetails? = null,
    @SerializedName("itemLists")
    val itemLists: List<ItemLists>? = null
) {
    @Keep
    data class CategoryDetails(
        @SerializedName("name")
        val name: String? = null
    )

    @Keep
    data class ItemLists(
        @SerializedName("product_name")
        val productName: String? = null,
        @SerializedName("product_price")
        val productPrice: Double? = null,
        @SerializedName("product_description")
        val productDescription: String? = null,
        @SerializedName("product_type")
        val productType: Int? = null,
        @SerializedName("product_image")
        val productImage: String? = null
    )
}