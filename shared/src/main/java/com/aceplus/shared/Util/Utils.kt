package com.aceplus.shared.Util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kkk on 9/22/2018.
 */
class Utils {
    companion object {
        fun getMonth(month: String): String {
            when (month) {
                "0" -> return "Jan"
                "1" -> return "Feb"
                "2" -> return "March"
                "3" -> return "April"
                "4" -> return "May"
                "5" -> return "June"
                "6" -> return "July"
                "7" -> return "Aug"
                "8" -> return "Sep"
                "9" -> return "Oct"
                "10" -> return "Nov"
                "11" -> return "Dec"
            }
            return ""
        }

        fun getTodayDateNode(): String {
            val calendar = Calendar.getInstance().time
            println("Current time => " + calendar)
            val df = SimpleDateFormat("dd-MM-yyyy")
            val formattedDate = df.format(calendar)
            return formattedDate
        }

        fun getRadomId(): String? {
            return (System.currentTimeMillis() / 1000).toString()
        }
    }
}