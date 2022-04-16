package com.android.houseofindia.network.models

import com.google.gson.annotations.SerializedName

data class HomeDataResponse( @SerializedName("success")
                             val success: Boolean? = null,
                             @SerializedName("message")
                             val message: String? = null,
                             @SerializedName("data")
                             val data: String? = null)
