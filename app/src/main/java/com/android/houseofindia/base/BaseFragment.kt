package com.android.houseofindia.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM: ViewModel?, VB: ViewBinding>: Fragment() {
    protected abstract val viewModel: VM?
    protected var binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = onCreateBinding(inflater, container)
        return binding?.root
    }

    abstract fun onCreateBinding(inflater: LayoutInflater, container: ViewGroup?): VB
}