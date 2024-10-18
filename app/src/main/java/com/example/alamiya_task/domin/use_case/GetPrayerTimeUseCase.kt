package com.example.alamiya_task.domin.use_case
import com.example.alamiya_task.common.util.Resource
import com.example.alamiya_task.data.model.PrayerTimeResponse
import com.example.alamiya_task.domin.repository.repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPrayerTimesUseCase @Inject constructor(
    private val repository: repository,
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
            val response = repository.getPrayerTimes(year, month, latitude, longitude, method)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection.", null))
        }
    }
}