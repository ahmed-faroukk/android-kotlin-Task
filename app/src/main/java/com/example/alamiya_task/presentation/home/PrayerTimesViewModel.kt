package com.example.alamiya_task.presentation.home

import com.example.alamiya_task.presentation.home.components.PermissionWidget
import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alamiya_task.core.helper_classes.LocationHelper
import com.example.alamiya_task.core.helper_classes.PermissionManager
import com.example.alamiya_task.core.state_handler.Resource
import com.example.alamiya_task.databinding.FragmentPrayerTimesBinding
import com.example.alamiya_task.domin.use_case.GetPrayerTimesUseCase
import com.example.alamiya_task.domin.entity.prayer_time.UserLocation
import com.example.alamiya_task.presentation.home.components.showSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PrayerTimesViewModel  @Inject constructor(
    private val getPrayerTimesUseCase: GetPrayerTimesUseCase,
) : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentDate = LocalDate.now()

    /** :::::::::::::::::::::::::::::live data objects ::::::::::::::::::::::::::::**/

    private val _prayerTimeState = MutableLiveData<PrayerTimeUiState>()
    val prayerTimeState: LiveData<PrayerTimeUiState> = _prayerTimeState


    private val _userLocation = MutableLiveData<UserLocation>()
    val userLocation: LiveData<UserLocation> = _userLocation


    fun setUserLocation(latitude: Double, longitude: Double , address : String) {
        _userLocation.value = UserLocation(latitude, longitude ,address)
    }


    /** ::::::::::::::::::::::::::::: get prayer time ::::::::::::::::::::::::::::**/

    fun getPrayerTimes(year: Int, month: Int, latitude: Double, longitude: Double, method: Int) {
        viewModelScope.launch {
            getPrayerTimesUseCase(year, month, latitude, longitude, method).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _prayerTimeState.value = PrayerTimeUiState(data = resource.data)
                    }
                    is Resource.Loading -> {
                        _prayerTimeState.value = PrayerTimeUiState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _prayerTimeState.value = PrayerTimeUiState(error = resource.message ?: "Unknown error")
                    }
                }
            }.launchIn(this)
        }
    }
    /** ::::::::::::::::::::::::::::: get user current location ::::::::::::::::::::::::::::**/

    fun getUserLocation(locationHelper: LocationHelper, binding: FragmentPrayerTimesBinding, widget : @Composable () -> Unit)  {
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

    /**::::::::::::::::::::::::::::: check location permission ::::::::::::::::::::::::::::*///

    fun checkLocationPermission(viewModel: PrayerTimesViewModel, binding: FragmentPrayerTimesBinding, permissionManager: PermissionManager, locationHelper: LocationHelper) {
        permissionManager.checkLocationPermission(
            onPermissionGranted = {
                viewModel.getUserLocation(locationHelper, binding) @Composable { PermissionWidget(permissionManager = permissionManager) }
            },
            onPermissionDenied = {
                showSettings(binding , permissionManager)

            }
        )
    }

}



