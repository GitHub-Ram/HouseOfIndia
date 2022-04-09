package com.android.houseofindia.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.android.houseofindia.HOIConstants
import com.android.houseofindia.R
import com.bumptech.glide.Glide

class ImageViewerFragment(private val url: String) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.dialog_image_viewer, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        isCancelable = false
        val foodImage = view.findViewById<ImageView>(R.id.iv_food_img)
        view.findViewById<ImageView>(R.id.iv_close).setOnClickListener { dismiss() }
        Glide.with(requireContext())
            .load(HOIConstants.IMAGE_URL + url.replace(" ","%20"))
            .placeholder(R.mipmap.loading)
            .into(foodImage)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}