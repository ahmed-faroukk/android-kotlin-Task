package com.example.alamiya_task.presentation.home

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alamiya_task.common.util.LocationHelper
import com.example.alamiya_task.common.util.Resource
import com.example.alamiya_task.data.model.PrayerTimeResponse
import com.example.alamiya_task.databinding.FragmentPrayerTimesBinding
import com.example.alamiya_task.domin.use_case.GetPrayerTimesUseCase
import com.example.alamiya_task.domin.use_case.PrayerUseCases
import com.example.alamiya_task.data.model.PrayerDate
import com.example.alamiya_task.data.model.UserLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PrayerTimesViewModel  @Inject constructor(
    private val prayerUseCase: PrayerUseCases,
    private val getPrayerTimesUseCase: GetPrayerTimesUseCase,
) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentDate = LocalDate.now()

    private val _state = MutableLiveData<PrayerTimeUiState>()
    val state: LiveData<PrayerTimeUiState> = _state

    private val _userLocation = MutableLiveData<UserLocation>()
    val userLocation: LiveData<UserLocation> = _userLocation
    fun setUserLocation(latitude: Double, longitude: Double , address : String) {
        _userLocation.value = UserLocation(latitude, longitude ,address)
    }



    /// ::::::::::::::::::::::::::::: get user current location ::::::::::::::::::::::::::::///

     fun getUserLocation(locationHelper: LocationHelper , binding: FragmentPrayerTimesBinding , widget : @Composable () -> Unit)  {
        locationHelper.fetchLocation(object : LocationHelper.OnLocationFetchedListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onLocationFetched(location: Location?, address: String?) {
                location?.let {
                    setUserLocation(it.latitude , it.longitude , address.toString())
                    getPrayerTimes(currentDate.year , currentDate.monthValue , it.latitude , it.longitude , 1)
                }
            }
            override fun onError(error: String?) {
                binding.myComposable.setContent {
                    widget()
                }
            }
        })
    }


    fun getPrayerTimes(year: Int, month: Int, latitude: Double, longitude: Double, method: Int) {
        viewModelScope.launch {
            getPrayerTimesUseCase(year, month, latitude, longitude, method).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.value = PrayerTimeUiState(data = resource.data)
                    }
                    is Resource.Loading -> {
                        _state.value = PrayerTimeUiState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _state.value = PrayerTimeUiState(error = resource.message ?: "Unknown error")
                    }
                }
            }.launchIn(this)
        }
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



