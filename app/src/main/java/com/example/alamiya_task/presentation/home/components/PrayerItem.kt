package com.example.alamiya_task.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alamiya_task.R
import com.example.alamiya_task.common.util.formatTimeTo12Hour

@Composable
fun PrayerItem(prayerName: String, prayerTime: String, nextPrayerName: String) {
    // Check if the current prayer is the next prayer
    val isNextPrayer = prayerName == nextPrayerName

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(
                color = if (isNextPrayer)  Color(0xFF4CAF50) else colorResource(id = R.color.secondary_color),
                shape = RoundedCornerShape(12.dp) // Rounded corners
            )
            .height(50.dp)
            .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically // Distribute space evenly
    ) {
        Text(
            text = prayerName,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.Black // Optional: Change text color for better contrast
        )

        Text(
            text = prayerTime.formatTimeTo12Hour(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            color = Color.Black // Optional: Change text color for better contrast
        )
    }
}
