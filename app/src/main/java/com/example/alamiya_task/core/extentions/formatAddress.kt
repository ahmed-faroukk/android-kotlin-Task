package com.example.alamiya_task.core.extentions

fun String.formatAddress(): String {
    // Use a regular expression to remove all numbers
    return this.replace(Regex("\\d+"), "").trim()
}