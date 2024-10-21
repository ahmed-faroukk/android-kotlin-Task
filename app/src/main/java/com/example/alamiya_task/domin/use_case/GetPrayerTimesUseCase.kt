package com.example.alamiya_task.domin.use_case
import android.util.Log
import com.example.alamiya_task.core.state_handler.Resource
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse
import com.example.alamiya_task.domin.repository.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPrayerTimesUseCase @Inject constructor(
    private val repository: PrayerRepository,
) {
    operator fun invoke(
        year: Int,
        month: Int,
        latitude: Double,
        longitude: Double,
        method: Int,
    ): Flow<Resource<PrayerTimeResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getCachedPrayerTimes(year, month, latitude, longitude, method)
            Log.d("response is ", response.toString())
            if (response == null ) {
                emit(Resource.Error( "no cashing data please connect with internet and try again", null))
            }else{
            emit(Resource.Success(response))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection.", null))
        }
    }
}