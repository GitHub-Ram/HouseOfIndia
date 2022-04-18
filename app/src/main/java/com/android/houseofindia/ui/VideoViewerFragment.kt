package com.android.houseofindia.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.VideoView
import androidx.fragment.app.DialogFragment
import com.android.houseofindia.R

class VideoViewerFragment() : DialogFragment() {
    private var foodVideo: VideoView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return inflater.inflate(R.layout.dialog_video_viewer, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        foodVideo = view.findViewById(R.id.video)
        view.findViewById<ImageView>(R.id.iv_close).setOnClickListener { dismiss() }
        foodVideo?.apply {
            setVideoURI(Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.video))
            start()
        }
    }

    override fun onResume() {
        super.onResume()
        if (foodVideo?.isPlaying == false) foodVideo?.start()
    }

    override fun onPause() {
        super.onPause()
        foodVideo?.pause()
    }

    override fun onStop() {
        super.onStop()
        foodVideo?.stopPlayback()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}