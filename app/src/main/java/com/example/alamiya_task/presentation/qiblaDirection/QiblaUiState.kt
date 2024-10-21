package com.example.alamiya_task.presentation.qiblaDirection

import com.example.alamiya_task.domin.entity.qibla.qiblaResponse

data class QiblaUiState(
    val data : qiblaResponse?= null,
    val isLoading : Boolean = false,
    val error : String = ""
)