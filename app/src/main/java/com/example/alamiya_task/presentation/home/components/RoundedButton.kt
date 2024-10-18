package com.example.alamiya_task.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R // Import your resource file

@Composable
fun RoundedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(id = R.color.secondary_color) // Default background color
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(50.dp), // Rounded shape
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor) // Set button color
    ) {
        Text(
            text = text,
            color = Color.Black // Text color
        )
    }
}
