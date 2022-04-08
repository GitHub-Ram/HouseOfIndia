package com.android.houseofindia.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.houseofindia.R
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<Nothing, FragmentSplashBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.video?.apply {
            setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.splash_video))
            start()
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.video?.start()
    }

    override fun onPause() {
        super.onPause()
        binding?.video?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.video?.stopPlayback()
    }

    override val viewModel = null

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }
}