package com.example.alamiya_task.presentation.home.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.alamiya_task.R

@Composable
fun ShimmerEffect(
    modifier: Modifier,
    durationMillis: Int = 1000,
) {
    val shimmerColors = listOf(
        Color.White.copy(alpha = 0.0f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.7f),
        Color.White.copy(alpha = 0.5f),
        Color.White.copy(alpha = 0.3f),
    )

    val transition = rememberInfiniteTransition(label = "shimmer")

    val translateAnimation = transition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer animation",
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(x = translateAnimation.value * 300f, y = 0f),
        end = Offset(x = translateAnimation.value * 300f + 1000f, y = 0f),
    )

    Column {
        Box(
            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(brush)
            )
        }
    }
}

@Composable
fun ShimmerList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_color))
    ) {
        item { Spacer(modifier = Modifier.height(50.dp)) }
        item { AzanImage() }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item{ ShimmerItem(height = 200) }
            items(10) { // Number of shimmer items to show
                ShimmerItem(height = 40)
                Spacer(modifier = Modifier.height(10.dp))
            }
    }
}

@Composable
fun ShimmerItem(height: Int = 100) {
    ShimmerEffect(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp) // Height of the shimmer item
    )
}

