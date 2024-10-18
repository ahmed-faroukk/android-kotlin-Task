package com.example.alamiya_task.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.alamiya_task.common.util.PermissionManager

@Composable
fun PermissionWidget(permissionManager: PermissionManager){
    Column(Modifier.fillMaxSize() , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "You are not authorized", color = Color.Black)
        Button(onClick = { permissionManager.openAppSettings() }) {
            Text("Open Settings to Enable Location Permission")
        }
    }
}