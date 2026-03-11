package com.ertugrulakkaya.spaceexplorer.presentation.util

import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

actual fun String.formatIsoToDate(): String {
   return try {
       val isoParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
           timeZone = java.util.TimeZone.getTimeZone("UTC")

       }
       val date = isoParser.parse(this)
       val localeFormatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
       date?.let {
           localeFormatter.format(it) } ?: this
   }catch (e : Exception){
       this
   }



}

actual fun String.formatIsoToDateTime(): String {
    return try {
        val isoParser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
            timeZone = java.util.TimeZone.getTimeZone("UTC")

        }
        val date = isoParser.parse(this)
        val localeFormatter = SimpleDateFormat("d MMMM yyyy - HH:mm", Locale.getDefault())
        date?.let {
            localeFormatter.format(it) } ?: this
    }catch (e : Exception){
        this
    }
}