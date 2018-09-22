package com.aceplus.shared.Util

import android.content.Context
import android.util.DisplayMetrics



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

        fun convertDpToPixel(dp: Float, context: Context): Float {
            val resources = context.getResources()
            val metrics = resources.getDisplayMetrics()
            return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        /**
         * This method converts device specific pixels to density independent pixels.
         *
         * @param px A value in px (pixels) unit. Which we need to convert into db
         * @param context Context to get resources and device specific display metrics
         * @return A float value to represent dp equivalent to px value
         */
        fun convertPixelsToDp(px: Float, context: Context): Float {
            return px / (context.getResources().getDisplayMetrics().densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

}