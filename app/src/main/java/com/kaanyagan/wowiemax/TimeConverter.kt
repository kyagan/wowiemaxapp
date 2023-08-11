package com.kaanyagan.wowiemax

import java.util.Calendar

class TimeConverter {
    companion object {
        fun convertMinutesToTime(minutes: Int): String {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, minutes / 60)
            calendar.set(Calendar.MINUTE, minutes % 60)

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            return "${hour}s ${minute}dk"
        }
    }
}