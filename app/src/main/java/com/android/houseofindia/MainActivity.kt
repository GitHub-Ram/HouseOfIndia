package com.android.houseofindia

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.houseofindia.base.BaseActivity
import com.android.houseofindia.databinding.ActivityMainBinding
import com.android.houseofindia.ui.IntroductionFragment
import com.android.houseofindia.ui.SplashFragment
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding.pager) {
            adapter = FoodPagerAdapter()
            setPageTransformer(BookFlipPageTransformer2())
            offscreenPageLimit = 2
        }
    }

    override fun onCreateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    inner class FoodPagerAdapter : FragmentStateAdapter(this) {

        override fun getItemCount(): Int = 3 //TODO: Change

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SplashFragment()
                1 -> IntroductionFragment(R.mipmap.introduction_1)
                2 -> IntroductionFragment(R.mipmap.introduction_2)
                else -> SplashFragment()
            }
        }
    }
}