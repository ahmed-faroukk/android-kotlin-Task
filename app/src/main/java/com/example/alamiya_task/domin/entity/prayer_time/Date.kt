package com.example.alamiya_task.domin.entity.prayer_time

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)