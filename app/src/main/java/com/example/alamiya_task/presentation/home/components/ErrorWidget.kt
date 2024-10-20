package com.example.alamiya_task.presentation.home.components

import CustomAppImage
import CustomAppText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R

@Composable
fun ErrorWidget(msg: String, retryAction: () -> Unit) {
    Column(
        modifier = Modifier.background(colorResource(id = R.color.primary_color)).padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        CustomAppImage(
            pathImg = R.drawable.baseline_error_outline_24,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(64.dp) // Adjust the size as needed
        )
        CustomAppText(
            text = msg,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Retry button
        Button(
            onClick = retryAction,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White), // Set button background to white
            shape = RoundedCornerShape(8.dp), // Optional: Add rounded corners
            modifier = Modifier.padding(8.dp) // Optional: Add padding around the button
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Retry",
                    tint = Color.Red, // Set icon color to red
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Retry", color = Color.Black) // You can set the text color as needed
            }
        }
    }
}