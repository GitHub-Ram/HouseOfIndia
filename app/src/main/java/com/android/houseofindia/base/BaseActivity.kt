package com.android.houseofindia.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    protected lateinit var binding: VB

    abstract fun onCreateBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        binding = onCreateBinding(layoutInflater)
        setContentView(binding.root)
    }

    private fun adjustFontScale(configuration: Configuration) {
        configuration.fontScale = 1.0f
        val metrics = resources.displayMetrics
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }
}