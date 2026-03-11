package com.ertugrulakkaya.spaceexplorer.presentation.util

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterLongStyle
import platform.Foundation.NSDateFormatterNoStyle
import platform.Foundation.NSDateFormatterShortStyle
import platform.Foundation.NSISO8601DateFormatWithFractionalSeconds
import platform.Foundation.NSISO8601DateFormatWithInternetDateTime
import platform.Foundation.NSISO8601DateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun String.formatIsoToDate(): String {
    val isoFormatter = NSISO8601DateFormatter().apply {
        formatOptions = NSISO8601DateFormatWithInternetDateTime or NSISO8601DateFormatWithFractionalSeconds
    }
    val date = isoFormatter.dateFromString(this) ?: return this

    val dateFormatter = NSDateFormatter().apply {
        dateStyle = NSDateFormatterLongStyle
        timeStyle = NSDateFormatterNoStyle
        locale = NSLocale.currentLocale
    }

    return dateFormatter.stringFromDate(date)
}

actual fun String.formatIsoToDateTime(): String {
    val isoFormatter = NSISO8601DateFormatter().apply {
        formatOptions = NSISO8601DateFormatWithInternetDateTime or NSISO8601DateFormatWithFractionalSeconds
    }
    val date = isoFormatter.dateFromString(this) ?: return this
    
    val dateFormatter = NSDateFormatter().apply {
        dateStyle = NSDateFormatterLongStyle
        timeStyle = NSDateFormatterShortStyle
        locale = NSLocale.currentLocale
    }


    return dateFormatter.stringFromDate(date)
}