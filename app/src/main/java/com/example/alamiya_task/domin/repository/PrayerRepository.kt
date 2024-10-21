package com.example.alamiya_task.domin.repository

import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse
import com.example.alamiya_task.domin.entity.qibla.qiblaResponse

interface PrayerRepository {
    suspend fun getCachedPrayerTimes(
        year: Int,
        month: Int,
        latitude: Double,
        longitude: Double,
        method: Int,
    ): PrayerTimeResponse?

    suspend fun getPrayerTimes(
        year: Int,
        month: Int,
        latitude: Double,
        longitude: Double,
        method: Int,
    ): PrayerTimeResponse

    suspend fun getQibla(
        latitude: Double,
        longitude: Double,
    ): qiblaResponse?

    suspend fun savePrayersTimes(response: PrayerTimeResponse): Long

    suspend fun getAllPrayersTimes(): PrayerTimeResponse

    suspend fun deleteAll()


}