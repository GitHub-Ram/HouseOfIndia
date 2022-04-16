package com.android.houseofindia

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.houseofindia.base.BaseActivity
import com.android.houseofindia.databinding.ActivityMainBinding
import com.android.houseofindia.network.ApiInterface
import com.android.houseofindia.network.ProductRepository
import com.android.houseofindia.network.models.CategoryResponse
import com.android.houseofindia.ui.*
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2
import kotlin.math.abs

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val linearMenuIdArray = arrayOf("1", "2", "4", "8", "9", "15")
    private val gridMenuIdArray = arrayOf("3", "5", "6", "7", "16", "17")
    private val visibleCategoryIds = arrayOf(
        listOf("1"), listOf("2"), listOf("3"), listOf("4", "15", "16"),
        listOf("5"), listOf("6"), listOf("7"), listOf("8", "17"), listOf("9")
    )
    private val viewModel: ProductViewModel by viewModels()
    private var categories: List<CategoryResponse.Category>? = null
    private var homeData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.productRepo = ProductRepository(HOIConstants.provideAPI(ApiInterface::class.java))
        with(binding.pager) {
            adapter = FoodPagerAdapter()
            setPageTransformer(BookFlipPageTransformer2())
            isUserInputEnabled = false
        }
        fetchCategories()
        fetchHomeData()
    }

    private fun fetchHomeData() {
        viewModel.getHomeData().observe(this) {
            // TODO: Hide Progress Bar
            if (it?.success == true && it.data != null && it.data.isNotEmpty()) {
                binding.pager.isUserInputEnabled = true
                homeData = it.data
            }
        }
    }

    private fun fetchCategories() {
        // TODO: Show Progress Bar
        viewModel.getCategories().observe(this) {
            // TODO: Hide Progress Bar
            if (it?.success == true && it.categoryList?.isNullOrEmpty() == false) {
                binding.pager.isUserInputEnabled = true
                categories = it.categoryList
                categories?.forEach { category ->
                    if (category.id?.isNotEmpty() == true) {
                        if (category.id in linearMenuIdArray)
                            category.isGrid = false
                        if (category.id in gridMenuIdArray)
                            category.isGrid = true
                    }
                }
                viewModel.fetchAllProducts(categories?.filter { cat ->
                    cat.id in (linearMenuIdArray + gridMenuIdArray)
                })
            }
        }
    }

    fun getProductViewModel() = viewModel

    override fun onCreateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    inner class FoodPagerAdapter : FragmentStateAdapter(this) {

        // TODO: Change below logic
        override fun getItemCount(): Int = 4 + visibleCategoryIds.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SplashFragment {
                    VideoViewerFragment().show(supportFragmentManager, "SplashVideo")
                }
                1 -> IntroductionFragment(R.mipmap.introduction_1)
                2 -> IntroductionFragment(
                    R.mipmap.hotel_menu_bg,
                    homeData
                ) // TODO: Change background image
                else -> {
                    if (position == 3 + visibleCategoryIds.size) {
                        FormFragment()
                    } else if (visibleCategoryIds[abs(3 - position)].size == 1) {
                        categories?.firstOrNull {
                            it.id == visibleCategoryIds[abs(3 - position)].first()
                        }?.let {
                            LinearGridMenuFragment(it)
                        } ?: IntroductionFragment(R.mipmap.introduction_1)
                    } else {
                        categories?.filter { it.id in visibleCategoryIds[abs(3 - position)] }
                            ?.let { groupCategories ->
                                MultipleMenuFragment(groupCategories)
                            } ?: IntroductionFragment(R.mipmap.introduction_1)
                    }
                }
            }
        }
    }
}