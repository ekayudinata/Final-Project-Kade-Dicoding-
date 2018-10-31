package com.example.yudinata.footballsc.util

import java.text.ParseException
import java.text.SimpleDateFormat


object SetDate {

    private fun formatDate(date: String, format: String): String {
        var result = ""
        if (date.equals(null)){
            return result
        }
        val old = SimpleDateFormat("yyyy-MM-dd")
        val oldDate = old.parse(date)
        val newFormat = SimpleDateFormat(format)
        result = newFormat.format(oldDate)
        return result
    }

    open fun getLongDate(date: String): String {
        return formatDate(date, "EEE, dd MMM yyyy")
    }
}