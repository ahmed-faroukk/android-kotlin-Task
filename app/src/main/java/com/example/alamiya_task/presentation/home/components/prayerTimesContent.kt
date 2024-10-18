package com.example.alamiya_task.presentation.home.components

import AzanImage
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R
import com.example.alamiya_task.common.util.helper_functions.findNextPrayer
import com.example.alamiya_task.data.model.PrayerTimeResponse
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimesScreen(prayerTimeResponse: PrayerTimeResponse, address: String) {
    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val currentTime = LocalTime.now()
    val timings = prayerTimeResponse.data[currentDate.value.dayOfMonth].timings
    val (nextPrayerName, nextPrayerTime) = findNextPrayer(currentTime, timings)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_color))
            .padding(5.dp)
    ) {
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item { AzanImage() }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item { LocationSection(onDateChange = { date -> currentDate.value = date }, address = address) }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { PrayerHeader(nextPrayerName, nextPrayerTime) }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { PrayerTimingsList(prayerTimeResponse.data[currentDate.value.dayOfMonth].timings, nextPrayerName = nextPrayerName) }
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item { ShowQiblaButton() }
        item { Spacer(modifier = Modifier.height(30.dp)) }
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
fun ShowQiblaButton() {
    RoundedButton(
        onClick = { /*TODO: Implement Qibla Direction functionality*/ },
        text = "Show Qibla Direction"
    )
}
