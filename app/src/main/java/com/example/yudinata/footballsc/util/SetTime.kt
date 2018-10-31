package com.example.yudinata.footballsc.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object SetTime {

   private fun formartTime(time:String): String {
            var result = ""
       if(time.equals(null)){
           return result
       } else{
           var OldFormat = SimpleDateFormat("HH:mm",Locale.getDefault())
           OldFormat.timeZone = TimeZone.getTimeZone("GMT")
           var  Oldtime = OldFormat.parse(time)
           var NewFormat = SimpleDateFormat("HH:mm")
           NewFormat.timeZone= TimeZone.getDefault()
           result = NewFormat.format(Oldtime)
           return result
       }

    }

    open fun getTime(time:String):String{  
        return formartTime(time)
    }
}