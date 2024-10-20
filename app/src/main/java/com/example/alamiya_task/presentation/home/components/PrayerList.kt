package com.example.alamiya_task.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.alamiya_task.domin.entity.prayer_time.Timings

@Composable
fun PrayerTimingsList(prayerTimeResponse: Timings, nextPrayerName : String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PrayerItem(prayerName = "Fajr", prayerTime = prayerTimeResponse.Fajr , nextPrayerName)
        PrayerItem(prayerName = "Sunrise", prayerTime = prayerTimeResponse.Sunrise,nextPrayerName)
        PrayerItem(prayerName = "Dhuhr", prayerTime = prayerTimeResponse.Dhuhr,nextPrayerName)
        PrayerItem(prayerName = "Asr", prayerTime = prayerTimeResponse.Asr,nextPrayerName)
        PrayerItem(prayerName = "Maghrib", prayerTime = prayerTimeResponse.Maghrib,nextPrayerName)
        PrayerItem(prayerName = "Isha", prayerTime = prayerTimeResponse.Isha,nextPrayerName)
    }
}
