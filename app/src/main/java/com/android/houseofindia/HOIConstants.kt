package com.android.houseofindia

import com.android.houseofindia.network.RestClient

object HOIConstants {
    const val IMAGE_URL = "http://www.webclickdigital.info/hoi/webroot/Admin/img/products/"
    const val FORM_URL =
        "https://docs.google.com/forms/d/1EYP6TySqL7IPyIpUE1fSsf12cFv54baupjH3XZmaS8c/viewform?ts=6254de6c&edit_requested=true"
    private val retrofit = RestClient().setupRestClient()

    fun <T> provideAPI(type: Class<T>) = retrofit.create(type)
}