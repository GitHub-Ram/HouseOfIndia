package com.android.houseofindia.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.houseofindia.R
import com.android.houseofindia.base.BaseFragment
import com.android.houseofindia.databinding.FragmentIntroductionBinding

class IntroductionFragment(private val imageId: Int, private val homeData: String? = null) :
    BaseFragment<Nothing, FragmentIntroductionBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.introImage?.setImageResource(imageId)
        binding?.godImage?.visibility = View.GONE
        if (!homeData.isNullOrEmpty()) {
            binding?.homeData?.visibility = View.VISIBLE
            binding?.godImage?.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding?.homeData?.text = Html.fromHtml(homeData, Html.FROM_HTML_MODE_COMPACT)
            } else {
                binding?.homeData?.text = Html.fromHtml(homeData)
            }
        } else {
            if (imageId == R.mipmap.hotel_menu_bg)
                binding?.introImage?.setImageResource(R.mipmap.introduction_2)
        }
    }

    override val viewModel = null

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentIntroductionBinding {
        return FragmentIntroductionBinding.inflate(inflater, container, false)
    }
}