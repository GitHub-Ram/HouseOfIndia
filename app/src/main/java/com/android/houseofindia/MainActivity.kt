package com.android.houseofindia

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.android.houseofindia.base.BaseActivity
import com.android.houseofindia.databinding.ActivityMainBinding
import com.android.houseofindia.network.ApiInterface
import com.android.houseofindia.network.ProductRepository

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.productRepo = ProductRepository(HOIConstants.provideAPI(ApiInterface::class.java))
        viewModel.getCategories().observe(this) {
            binding.tv.text = it?.categoryList?.joinToString { c -> c.name ?: "NA" } ?: "NA"
        }
    }

    override fun onCreateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}