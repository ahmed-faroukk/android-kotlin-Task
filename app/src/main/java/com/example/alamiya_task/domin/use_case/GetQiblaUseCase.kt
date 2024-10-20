package com.example.alamiya_task.domin.use_case

import android.util.Log
import com.example.alamiya_task.core.state_handler.Resource
import com.example.alamiya_task.domin.entity.qibla.qiblaResponse
import com.example.alamiya_task.domin.repository.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQiblaUseCase @Inject constructor(
    private val repository: PrayerRepository,
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double,
    ): Flow<Resource<qiblaResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getQibla(latitude, longitude)
            Log.d("response is ", response.toString())
            if (response == null ) {
                emit(Resource.Error( "no cashing data please connect with internet and try again", null))
            }else
                emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", null))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection.", null))
        }
    }
}