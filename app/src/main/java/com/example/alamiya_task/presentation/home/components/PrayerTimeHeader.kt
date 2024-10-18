package com.example.alamiya_task.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alamiya_task.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimeHeader(
    nextPrayer: String,
    currentPrayer: String,
    nextPrayerTime: LocalTime
) {
    var countdown by remember { mutableStateOf("") }

    LaunchedEffect(nextPrayerTime) {
        while (true) {
            val currentTime = LocalTime.now()
            var remainingTime = nextPrayerTime.toSecondOfDay() - currentTime.toSecondOfDay()

            countdown = if (remainingTime > 0) {
                val hours = remainingTime / 3600
                val minutes = (remainingTime % 3600) / 60
                val seconds = remainingTime % 60
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            } else {
                remainingTime+=24*60*60
                val hours = remainingTime  / 3600
                val minutes = (remainingTime % 3600) / 60
                val seconds = remainingTime % 60
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }

            kotlinx.coroutines.delay(1000)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.secondary_color), RoundedCornerShape(12.dp))
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Next Prayer
        Text(
            text = nextPrayer,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Current Prayer Time
        Text(
            text = currentPrayer,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        // Countdown to Next Prayer
        Text(
            text = countdown,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.primary_color)
        )
    }
}
