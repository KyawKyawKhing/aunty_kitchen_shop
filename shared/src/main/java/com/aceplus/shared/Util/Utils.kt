package com.aceplus.shared.Util

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
    }
}