package com.android.houseofindia

import com.android.houseofindia.network.RestClient

object HOIConstants {
    const val IMAGE_URL = "http://www.webclickdigital.info/hoi/webroot/Admin/img/products/"
    private val retrofit = RestClient().setupRestClient()

    fun <T> provideAPI(type: Class<T>) = retrofit.create(type)
}