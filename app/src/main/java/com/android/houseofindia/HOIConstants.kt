package com.android.houseofindia

import com.android.houseofindia.network.RestClient

object HOIConstants {
    private val retrofit = RestClient().setupRestClient()

    fun <T> provideAPI(type: Class<T>) = retrofit.create(type)
}