package com.example.alamiya_task.core.extentions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yy")
    return this.format(formatter)
}