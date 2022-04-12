package com.exseed.health.utils

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun setText(textView: TextView, value: String) {
        textView.text = value
    }

    /*
    * function to convert any long number into the string representation e.g 1000 to 1k
    *  */
    fun format(value: Long, suffixes: NavigableMap<Long, String>): String {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1, suffixes)
        if (value < 0) return "-" + format(-value, suffixes)
        if (value < 1000) return java.lang.Long.toString(value) //deal with easy case
        val (divideBy, suffix) = suffixes.floorEntry(value)
        val truncated = value / (divideBy / 10) //the number part of the output times 10
        val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
        return if (hasDecimal) (truncated / 10.0).toString() + suffix else (truncated / 10).toString() + suffix
    }

    fun parseDateTime(date: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormat = SimpleDateFormat("MMM d,yyyy HH:mm")
        return outputFormat.format(dateFormat.parse(date))
    }
}