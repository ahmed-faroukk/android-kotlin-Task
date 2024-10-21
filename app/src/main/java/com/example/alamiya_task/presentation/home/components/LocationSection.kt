package com.example.alamiya_task.presentation.home.components

import CustomAppText
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R
import com.example.alamiya_task.core.extentions.formatDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocationSection(
    onDateChange: (LocalDate) -> Unit,
    address: String // Callback to notify date changes
) {
    // Remember the current date as a state variable
    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val currentMonthDays = currentDate.value.month.length(currentDate.value.isLeapYear)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(colorResource(id = R.color.secondary_color), RoundedCornerShape(12.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Arrow Icon
        IconButton(
            onClick = {
                if (currentDate.value.dayOfMonth > 1) {
                    // Move to the previous day
                    val previousDate = currentDate.value.minusDays(1)
                    currentDate.value = previousDate
                    onDateChange(previousDate)
                }
            },
            modifier = Modifier.weight(1f),
            enabled = currentDate.value.dayOfMonth > 1 // Disable if it's the first day
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Left Arrow"
            )
        }

        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            CustomAppText(
                text = currentDate.value.formatDate(), // Display current date
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomAppText(
                text = address ,
                textAlign = TextAlign.Center,
                fontSize = 14f,
                maxLines = 3,
                color = colorResource(id = R.color.primary_color)
            )
            Spacer(modifier = Modifier.height(5.dp))

        }

        IconButton(
            onClick = {
                if (currentDate.value.dayOfMonth < currentMonthDays-1) {
                    // Move to the next day
                    val nextDate = currentDate.value.plusDays(1)
                    currentDate.value = nextDate
                    onDateChange(nextDate)
                }
            },
            modifier = Modifier.weight(1f),
            enabled = currentDate.value.dayOfMonth < currentMonthDays-1 // Disable if it's the last day
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Right Arrow"
            )
        }
    }
}
