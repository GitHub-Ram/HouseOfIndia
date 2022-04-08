package com.android.houseofindia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.databinding.FragmentIntroductionBinding

class IntroductionFragment(private val imageId: Int): BaseFragment<Nothing, FragmentIntroductionBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.introImage?.setImageResource(imageId)
    }

    override val viewModel = null

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIntroductionBinding {
        return FragmentIntroductionBinding.inflate(inflater, container, false)
    }
}