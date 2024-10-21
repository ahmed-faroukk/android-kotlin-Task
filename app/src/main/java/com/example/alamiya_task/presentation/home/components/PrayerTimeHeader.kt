package com.example.alamiya_task.presentation.home.components

import CustomAppText
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R
import com.example.alamiya_task.core.extentions.formatCountdownToNextPrayer
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerTimeHeader(
    nextPrayer: String,
    currentPrayer: String,
    nextPrayerTime: LocalTime
) {
    var countdown by remember { mutableStateOf("") }

    LaunchedEffect(nextPrayerTime) {
        LocalTime.now().formatCountdownToNextPrayer(nextPrayerTime) { formattedCountdown ->
            countdown = formattedCountdown
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
        CustomAppText(
            text = nextPrayer,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 17f,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        CustomAppText(
            text = currentPrayer,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 17f,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        CustomAppText(
            text = countdown,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 17f,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.primary_color)
        )
    }
}
