package com.example.footbal360.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.footbal360.R
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

// Unix timestamp
@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun timeDifference(timestamp: Long): String {
    // Convert to Instant
    val instant = Instant.ofEpochSecond(timestamp)

    // Convert to LocalDateTime
    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    // Get current time
    val now = LocalDateTime.now()

    // Calculate the difference
    val duration = Duration.between(dateTime, now)

    fun changeToPersianNumber(number: String): String {
        val persianNumber = number
            .replace("1","۱")
            .replace("2","۲")
            .replace("3","۳")
            .replace("4","۴")
            .replace("5","۵")
            .replace("6","۶")
            .replace("7","۷")
            .replace("8","۸")
            .replace("9","۹")
            .replace("0","۰")
        return persianNumber
    }

    // Format the difference
    val timeAgo = when {
        duration.toDays() > 0 -> "${changeToPersianNumber(duration.toDays().toString()) } ${stringResource(id = R.string.days_ago)}"
        duration.toHours() > 0 -> "${changeToPersianNumber(duration.toHours().toString())} ${stringResource(id = R.string.hours_ago)}"
        duration.toMinutes() > 0 -> "${changeToPersianNumber(duration.toMinutes().toString())} ${stringResource(id = R.string.minutes_ago)}"
        else -> stringResource(id = R.string.now)
    }
//    Log.d("Mehrdad timeFormat", "timeDifference: $timeAgo")
    return timeAgo
}
