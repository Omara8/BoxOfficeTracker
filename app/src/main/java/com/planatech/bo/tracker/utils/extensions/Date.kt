package com.planatech.bo.tracker.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatForAPI(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return sdf.format(this).toString()
}