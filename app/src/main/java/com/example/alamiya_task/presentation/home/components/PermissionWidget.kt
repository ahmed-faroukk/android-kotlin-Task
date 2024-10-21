package com.example.alamiya_task.presentation.home.components
import CustomAppText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R
import com.example.alamiya_task.core.helper_classes.PermissionManager

@Composable
fun PermissionWidget(permissionManager: PermissionManager) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
          ,
        color = MaterialTheme.colorScheme.background // Set a background color from your theme
    ) {
        Column(
            modifier = Modifier.background(colorResource(id = R.color.primary_color)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_location_on_24), // Use painterResource to load the drawable
                contentDescription = "Location Permission",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(64.dp)
            )
            CustomAppText(
                text = "The app needs location permission to get accurate prayer times based on your location.",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(24.dp)
            )
            Button(
                onClick = { permissionManager.openAppSettings() },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Red
                ),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "Open Settings",
                )
            }
        }
    }
}
