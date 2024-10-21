package com.example.alamiya_task.data.Repository

import com.example.alamiya_task.core.connectivity_observer.NetworkState
import com.example.alamiya_task.data.source.Database.PrayerDatabase
import com.example.alamiya_task.data.source.RemoteData.ApiInterface
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse
import com.example.alamiya_task.domin.entity.qibla.qiblaResponse
import com.example.alamiya_task.domin.repository.PrayerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PrayerRepositoryImp @Inject constructor(
    private val apiInterface: ApiInterface,
    private val db: PrayerDatabase,
    private val networkState: NetworkState,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PrayerRepository {

    override suspend fun getCachedPrayerTimes(
        year: Int,
        month: Int,
        latitude: Double,
        longitude: Double,
        method: Int,
    ): PrayerTimeResponse {
        if (networkState.isOnline()) {
            withContext(dispatcher) {
                val response = getPrayerTimes(year, month, latitude, longitude, method)
                savePrayersTimes(response)
            }
        }
        return getAllPrayersTimes()
    }

    override suspend fun getPrayerTimes(
        year: Int,
        month: Int,
        latitude: Double,
        longitude: Double,
        method: Int,
    ): PrayerTimeResponse = apiInterface.getPrayerTimes(year, month, latitude, longitude, method)


    override suspend fun getQibla(latitude: Double, longitude: Double): qiblaResponse =
        apiInterface.getQiblaDirection(latitude, longitude)

    override suspend fun savePrayersTimes(response: PrayerTimeResponse): Long =
        db.getPrayerDao().savePrayersTimes(response)


    override suspend fun getAllPrayersTimes(): PrayerTimeResponse {
        return withContext(dispatcher) {
            db.getPrayerDao().getAllPrayersTimes()
        }
    }


    override suspend fun deleteAll() = db.getPrayerDao().deleteAll()


}