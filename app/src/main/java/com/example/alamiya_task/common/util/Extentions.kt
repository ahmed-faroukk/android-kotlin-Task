package com.example.alamiya_task.common.util

import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.alamiya_task.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


fun String.formatTimeTo12Hour(): String {
    val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        this // Return the original string if parsing fails
    }
}

fun ImageView.compassHandler(azimuth: Float ,qiblaDirection : Double ){

    val azimuthNormalized = (azimuth + 360) % 360 // Convert to a positive value
    val angleToQibla = (azimuthNormalized - qiblaDirection + 360) % 360
    if (angleToQibla < 10f || angleToQibla > 350f) {
        this.setImageResource(R.drawable.baseline_explore_25)
    } else {
        this.setImageResource(R.drawable.baseline_explore_24)
    }
}

fun String.formatAddress(): String {
    // Use a regular expression to remove all numbers
    return this.replace(Regex("\\d+"), "").trim()
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yy")
    return this.format(formatter)
}