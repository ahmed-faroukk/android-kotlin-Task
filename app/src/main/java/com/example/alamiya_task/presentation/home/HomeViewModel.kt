package com.example.alamiya_task.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alamiya_task.common.util.Resource
import com.example.alamiya_task.data.model.PrayerTimeResponse
import com.example.alamiya_task.domin.use_case.GetPrayersTimesUseCase
import com.example.alamiya_task.domin.use_case.PrayerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prayerUseCase: PrayerUseCases,
) : ViewModel() {

    //livedata objects
    private val _getPrayerTimeState = MutableLiveData<Resource<PrayerTimeResponse>>()
    val getPrayerTimeState: LiveData<Resource<PrayerTimeResponse>> = _getPrayerTimeState

    //livedata objects
    private val _prayerTimes = MutableLiveData<List<String>>()
    val prayerTimes: LiveData<List<String>> = _prayerTimes

    //latitude
    private val _lat = MutableLiveData<Double>()
    val lat: LiveData<Double> = _lat

    //Longitude
    private val _long = MutableLiveData<Double>()
    val long: LiveData<Double> = _long

    //livedata objects
    private val _day = MutableLiveData<Int>()

    //livedata objects
    private val _month = MutableLiveData<Int>()

    //livedata objects
    private val _year = MutableLiveData<Int>()



    //livedata objects
    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> = _index


    fun setLat(latitude: Double) {
        _lat.value = latitude
    }

    fun setLong(longitude: Double) {
        _long.value = longitude
    }

    fun setDay(day: Int) {
        _day.postValue(day)
    }

    fun setMonth(month: Int) {
        _month.postValue(month)
    }

    fun setYear(year: Int) {
        _year.postValue(year)
    }

    fun setPrayerTimes(PrayerTimes: List<String>) {
        _prayerTimes.postValue(PrayerTimes)
    }

    fun setIndex(index: Int) {
        _index.postValue(index)
    }


    /// ::::::::::::::::::::::: get all prayer times based on location ::::::::::::::::::: ///
    fun getPrayerTimes(
        year: Int,
        month: Int,
        latitude: Double,
        longitude: Double,
        method: Int,
    ) {
        viewModelScope.launch {
            try {
                val response = prayerUseCase.getPrayersTimesUseCase(year, month, latitude, longitude, method)
              // _getPrayerTimeState.value = getPrayerTimeHandler(response)

            } catch (t: Throwable) {
                _getPrayerTimeState.postValue(Resource.Error(t.message, null))
            }
        }
    }

    /// ::::::::::::::::::::::: get all prayer times based on location ::::::::::::::::::: ///
    private fun getPrayerTimeHandler(response: Response<PrayerTimeResponse>): Resource<PrayerTimeResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message(), null)
    }


    fun saveAllPrayersTimes(response: PrayerTimeResponse) =
        viewModelScope.launch {
            prayerUseCase.savePrayersTimesUseCase(response)

        }


    fun getAllPrayersTimes(): LiveData<PrayerTimeResponse> {
        return prayerUseCase.getAllPrayersTimesUseCase()
    }

    fun deleteAll() {
        viewModelScope.launch {
            prayerUseCase.deleteTableUseCase()
        }
    }


}