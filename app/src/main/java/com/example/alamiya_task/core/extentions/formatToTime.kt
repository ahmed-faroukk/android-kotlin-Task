package com.example.alamiya_task.core.extentions

fun Int.formatCountDown(): String {
    val hours = (this / 3600) % 24
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}
