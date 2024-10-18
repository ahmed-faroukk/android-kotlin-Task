package com.example.alamiya_task.presentation.home

import com.example.alamiya_task.data.model.PrayerTimeResponse

data class PrayerTimeUiState(
    val data : PrayerTimeResponse ?= null  ,
    val isLoading : Boolean = false ,
    val error : String = ""
)