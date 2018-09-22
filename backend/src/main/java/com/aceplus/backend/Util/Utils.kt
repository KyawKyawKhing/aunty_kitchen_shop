package com.aceplus.backend.Util

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by kkk on 9/22/2018.
 */
class Utils {
    companion object {
        fun getTodayDateNode(): String {
            val calendar = Calendar.getInstance().time
            println("Current time => " + calendar)
            val df = SimpleDateFormat("dd-MM-yyyy")
            val formattedDate = df.format(calendar)
            return formattedDate
        }
    }
}