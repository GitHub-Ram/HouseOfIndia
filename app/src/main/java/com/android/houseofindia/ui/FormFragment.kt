package com.android.houseofindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.houseofindia.R
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.databinding.FragmentFormBinding
import com.android.houseofindia.databinding.FragmentIntroductionBinding

class FormFragment (): BaseFragment<Nothing, FragmentFormBinding>() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the web view settings instance.
        val settings = binding?.webView?.settings

        // Enable java script in web view.
        settings?.javaScriptEnabled = true

        // Enable DOM storage API.
        settings?.domStorageEnabled = true

        // Enable zooming in web view.
        settings?.setSupportZoom(true)

        // Allow pinch to zoom.
        settings?.builtInZoomControls = true

        // Disable the default zoom controls on the page.
        settings?.displayZoomControls = false

        // Enable responsive layout.
        settings?.useWideViewPort = false

        // Zoom out if the content width is greater than the width of the viewport.
        settings?.loadWithOverviewMode = false


        // Set web view client.
        binding?.webView?.webChromeClient = object : DefaultWebChromeClient() {

            override fun onProgressChanged(webView: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                if (newProgress == 100) {

                    // There is a bug with Google Docs that sometimes you get blank screen
                    // instead of a PDF file. To avoid just reload when you get it.
//                    if (webView?.contentHeight == 0 && reloadCount < RELOAD_ALLOW) {
//                        Log.w("LOG_TAG", "PDF loading error. Reloading $reloadCount.")
//                        Toast.makeText(activity, "Error. Reloading...", Toast.LENGTH_SHORT).show()
//                        reloadCount++
//                        webView.reload()
//                    }

                        binding?.progressBar?.visibility = View.GONE
                }
            }

        }
        binding?.webView?.loadUrl(url)

        Log.d("LOG_TAG", "Loading URL: $url")
    }

    override val viewModel = null

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormBinding {
        return FragmentFormBinding.inflate(inflater, container, false)
    }

    private var url: String = "https://docs.google.com/forms/d/1EYP6TySqL7IPyIpUE1fSsf12cFv54baupjH3XZmaS8c/viewform?ts=6254de6c&edit_requested=true"

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_form, container, false)

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        binding?.webView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding?.webView?.onPause()
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


    fun onBackPressed(): Boolean {
        binding?.webView?.let {
            if (it.canGoBack()) {
                // If web view have back history, then go to the web view back history.
                it.goBack()
                return true
            }
        }
        return false
    }


    internal open class DefaultWebViewClient : WebViewClient() {

        // Decide how a new url will be loaded. If this method returns false, it means current
        // webView will handle the url. If this method returns true, it means host application
        // will handle the url. By default, redirects cause jump from WebView to default
        // system browser.
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return true
        }

    }

    internal open class DefaultWebChromeClient : WebChromeClient() {

    }
}