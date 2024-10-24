package com.example.alamiya_task.presentation.qiblaDirection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alamiya_task.core.state_handler.Resource
import com.example.alamiya_task.domin.use_case.GetQiblaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QiblaViewModel @Inject constructor(
private val getQiblaUseCase: GetQiblaUseCase
) : ViewModel() {

    /** ::::::::::::::::::::::::::::: live data objects ::::::::::::::::::::::::::::**/

    private val _qiblaState = MutableLiveData<QiblaUiState>()
    val qiblaState: LiveData<QiblaUiState> = _qiblaState

    /** ::::::::::::::::::::::::::::: get direction state ::::::::::::::::::::::::::::**/

    fun getQibla( latitude: Double, longitude: Double) {
        viewModelScope.launch {
            getQiblaUseCase( latitude, longitude).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _qiblaState.value = QiblaUiState(data = resource.data)
                    }
                    is Resource.Loading -> {
                        _qiblaState.value = QiblaUiState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _qiblaState.value = QiblaUiState(error = resource.message ?: "Unknown error")
                    }
                }
            }.launchIn(this)
        }
    }




}