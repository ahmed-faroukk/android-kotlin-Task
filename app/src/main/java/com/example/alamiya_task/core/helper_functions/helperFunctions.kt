package com.example.alamiya_task.core.helper_functions

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.alamiya_task.domin.entity.prayer_time.Timings
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
fun findNextPrayer(currentTime: LocalTime, timings: Timings): Pair<String, LocalTime> {
    val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

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

    return Pair("Fajr", LocalTime.parse(timings.Fajr.split(" ")[0], formatter).plusHours(24))
}




