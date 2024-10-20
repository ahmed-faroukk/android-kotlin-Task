package com.example.alamiya_task.core.extentions

import android.widget.ImageView
import com.example.alamiya_task.R

fun ImageView.compassHandler(azimuth: Float, qiblaDirection : Double ){

    val azimuthNormalized = (azimuth + 360) % 360 // Convert to a positive value
    val angleToQibla = (azimuthNormalized - qiblaDirection + 360) % 360
    if (angleToQibla < 10f || angleToQibla > 350f) {
        this.setImageResource(R.drawable.baseline_explore_25)
    } else {
        this.setImageResource(R.drawable.baseline_explore_24)
    }
}