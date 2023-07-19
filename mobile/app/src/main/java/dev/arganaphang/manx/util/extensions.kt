package dev.arganaphang.manx.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Date
import java.util.Locale

fun Long.toMoney(): String {
    val format: NumberFormat = DecimalFormat.getCurrencyInstance(Locale("id", "ID"))
    format.maximumFractionDigits = 0
    return format.format(this)
}

fun Date.toHuman(): String {
    var elapsedTime = ""
    val instantInThePast = Instant.fromEpochMilliseconds(this.time)
    val period = instantInThePast.periodUntil(Clock.System.now(), TimeZone.UTC)
    val seconds = period.seconds
    val minutes = period.minutes
    val hours = period.hours
    val days = period.days
    val months = period.months
    val years = period.years
    if (years != 0)
        elapsedTime = if (years == 1)
            "$years year ago"
        else
            "$years years ago"
    else if (months != 0)
        elapsedTime = if (months == 1)
            "$months month ago"
        else
            "$months months ago"
    else if (days != 0)
        elapsedTime = if (days == 1)
            "Yesterday"
        else
            "$days days ago"
    else if (hours != 0)
        elapsedTime = if (hours == 1)
            "$hours hour ago"
        else
            "$hours hours ago"
    else if (minutes != 0)
        elapsedTime = if (minutes == 1)
            "$minutes minute ago"
        else
            "$minutes minutes ago"
    else if (seconds != 0)
        elapsedTime = if (seconds == 1)
            "$seconds second ago"
        else
            "$seconds seconds ago"
    return elapsedTime
}