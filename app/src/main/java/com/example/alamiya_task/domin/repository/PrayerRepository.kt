package com.example.alamiya_task.domin.repository

import com.example.alamiya_task.core.state_handler.Resource
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse
import com.example.alamiya_task.domin.entity.qibla.qiblaResponse
import com.google.android.gms.common.api.Response

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