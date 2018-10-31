package com.example.yudinata.footballsc.util

import com.example.yudinata.footballsc.util.SetDate.getLongDate
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

class SetDateTest {

    @Test
    fun testgetLongDate() {

        assertEquals("Wed, 10 Oct 2018", getLongDate("2018-10-10"))
    }

}