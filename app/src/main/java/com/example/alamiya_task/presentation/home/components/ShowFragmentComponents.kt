package com.example.alamiya_task.presentation.home.components

import ShimmerEffect
import ShimmerList
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R
import com.example.alamiya_task.common.util.PermissionManager
import com.example.alamiya_task.data.model.PrayerTimeResponse
import com.example.alamiya_task.databinding.FragmentPrayerTimesBinding

@RequiresApi(Build.VERSION_CODES.O)
fun showPrayerTimesScreen(prayerTimeResponse: PrayerTimeResponse, address: String , binding: FragmentPrayerTimesBinding) {
        // Set the composable content for prayer times only if permission is granted
        binding.myComposable.setContent {
            PrayerTimesScreen(prayerTimeResponse = prayerTimeResponse, address = address)
        }
    }
 fun showLoading(binding: FragmentPrayerTimesBinding) {
    // Set the composable content for prayer times only if permission is granted
    binding.myComposable.setContent {
        ShimmerList()
    }
}

 fun showError(retryAction: () -> Unit , binding: FragmentPrayerTimesBinding) {
    // Set the composable content to show an error message with a retry icon
    binding.myComposable.setContent {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Error message
            Text(
                text = "Something went wrong, please try again.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Retry button
            Button(onClick = retryAction) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Retry",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Retry")
                }
            }
        }
    }
}

 fun showSettings(binding: FragmentPrayerTimesBinding , permissionManager: PermissionManager) {
    binding.myComposable.setContent {
        PermissionWidget(permissionManager = permissionManager)
    }
}
