package com.android.houseofindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.databinding.FragmentSplashBinding

class SplashFragment(private val onPlayVideo: () -> Unit) :
    BaseFragment<Nothing, FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.playVideo?.setOnClickListener { onPlayVideo() }
    }

    override val viewModel = null

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }
}