package com.example.alamiya_task.presentation.home.components

import AzanImage
import CustomAppButton
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R
import com.example.alamiya_task.core.helper_functions.findNextPrayer
import com.example.alamiya_task.domin.entity.prayer_time.PrayerTimeResponse
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimesScreen(
    prayerTimeResponse: PrayerTimeResponse,
    address: String,
    onButtonClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(false) } // Default to false to allow animation on first load

    LaunchedEffect(Unit) {
        isVisible = true
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_color)) // Background color for the whole screen
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(durationMillis = 1500)) // Expands vertically
        ) {
            PrayerTimesContent(prayerTimeResponse, address, onButtonClick)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimesContent(
    prayerTimeResponse: PrayerTimeResponse,
    address: String,
    onButtonClick: () -> Unit
) {

    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val currentTime = LocalTime.now()
    val timingsOfToday =  prayerTimeResponse.data[ LocalDate.now().dayOfMonth].timings
    val (nextPrayerName, nextPrayerTime) = findNextPrayer(currentTime, timingsOfToday)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_color))
            .padding(5.dp)
    ) {
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item { AzanImage() }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            LocationSection(onDateChange = { date -> currentDate.value = date }, address = address)

        }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { PrayerHeader(nextPrayerName, nextPrayerTime) }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item {
            PrayerTimingsList(prayerTimeResponse.data[currentDate.value.dayOfMonth].timings, nextPrayerName = nextPrayerName)
            Spacer(modifier = Modifier.height(30.dp))
            ShowQiblaButton { onButtonClick() }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun PrayerHeader(nextPrayerName: String, nextPrayerTime: LocalTime) {

    PrayerTimeHeader(
        nextPrayer = "Next Prayer",
        currentPrayer = nextPrayerName,
        nextPrayerTime = nextPrayerTime // Assuming this is a LocalTime object
    )
}

@Composable
fun ShowQiblaButton(onButtonClick: () -> Unit) {
    CustomAppButton(
        onTap = {
            onButtonClick()
        },
        text = "Show Qibla Direction"
    )
}
