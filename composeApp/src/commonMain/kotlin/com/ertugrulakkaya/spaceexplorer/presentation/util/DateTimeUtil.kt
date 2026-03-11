package com.ertugrulakkaya.spaceexplorer.presentation.util

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun Instant.toDate(): String {
    val dateTime = this.toLocalDateTime(TimeZone.UTC)
    val month = dateTime.month.name.lowercase().replaceFirstChar {
        it.uppercase()

    }
    return "$month ${dateTime.day}, ${dateTime.year}"
}

fun Instant.toFullDateTime(): String {
    val dateTime = this.toLocalDateTime(TimeZone.UTC)
    val month =
        dateTime.month.name.lowercase().replaceFirstChar {
            it.uppercase()
        }
    val hour = dateTime.hour.toString().padStart(2, '0')
    val minute = dateTime.minute.toString().padStart(
        2,
        '0'
    )
    return "$month ${dateTime.day}, ${dateTime.year} - $hour:$minute"
}

expect fun String.formatIsoToDate() : String

expect fun String.formatIsoToDateTime() : String