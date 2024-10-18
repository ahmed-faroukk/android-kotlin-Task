package com.example.alamiya_task.common.util.helper_functions

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.alamiya_task.data.model.Timings
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale



private fun convertTimeToMilliseconds(timeString: String): Long {
    return try {
        val format = SimpleDateFormat("hh:mm a", Locale.US)
        val date = format.parse(timeString)
        date?.time ?: -1
    } catch (e: Exception) {
        -1
    }
}

// Helper function to find the next prayer
@RequiresApi(Build.VERSION_CODES.O)
fun findNextPrayer(currentTime: LocalTime, timings: Timings): Pair<String, LocalTime> {
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

    // List to hold the next prayer names and times
    val prayerTimes = listOf(
        "Fajr" to LocalTime.parse(timings.Fajr.split(" ")[0], formatter),
        "Sunrise" to LocalTime.parse(timings.Sunrise.split(" ")[0], formatter),
        "Dhuhr" to LocalTime.parse(timings.Dhuhr.split(" ")[0], formatter),
        "Asr" to LocalTime.parse(timings.Asr.split(" ")[0], formatter),
        "Maghrib" to LocalTime.parse(timings.Maghrib.split(" ")[0], formatter),
        "Isha" to LocalTime.parse(timings.Isha.split(" ")[0], formatter)
    )

    for ((prayerName, prayerTime) in prayerTimes) {
        if (prayerTime.isAfter(currentTime)) {
            return Pair(prayerName, prayerTime)
        }
    }

    // If no next prayer found, return Fajr of the next day
    return Pair("Fajr", LocalTime.parse(timings.Fajr.split(" ")[0], formatter).plusHours(24))
}