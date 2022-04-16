package com.android.houseofindia.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.houseofindia.HOIConstants
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.databinding.FragmentFormBinding

class FormFragment : BaseFragment<Nothing, FragmentFormBinding>() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.webView?.apply {
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding?.progressBar?.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding?.progressBar?.visibility = View.GONE
                }
            }
            with(settings) {
                javaScriptEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                builtInZoomControls = true
                displayZoomControls = false
                domStorageEnabled = true
                allowFileAccess = true
            }
            loadUrl(HOIConstants.FORM_URL)
        }
    }

    override val viewModel = null

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormBinding {
        return FragmentFormBinding.inflate(inflater, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Destroy the WebView completely.
        binding?.webView?.let {
            // The WebView must be removed from the view hierarchy before calling destroy to prevent a memory leak.
            (it.parent as ViewGroup).removeView(it)
            it.removeAllViews()
            it.destroy()
        }
    }

    /*fun onBackPressed(): Boolean {
        binding?.webView?.let {
            if (it.canGoBack()) {
                // If web view have back history, then go to the web view back history.
                it.goBack()
                return true
            }
        }
        return false
    }*/
}