package com.example.alamiya_task.core.extentions

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.delay
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
suspend fun LocalTime.formatCountdownToNextPrayer(nextPrayerTime: LocalTime, updateCountdown: (String) -> Unit) {
    while (true) {
        val currentTime = LocalTime.now()
        var remainingTime = nextPrayerTime.toSecondOfDay() - currentTime.toSecondOfDay()

        val countdown = if (remainingTime > 0) {
            remainingTime.formatCountDown()
        } else {
            remainingTime += 24 * 60 * 60 // Add 24 hours in seconds
            remainingTime.formatCountDown()
        }

        // Update the countdown in the calling context
        updateCountdown(countdown)
        delay(1000)
    }
}
