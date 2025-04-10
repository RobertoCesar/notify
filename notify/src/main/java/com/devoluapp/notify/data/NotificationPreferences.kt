package com.devoluapp.notify.data

import android.content.Context
import android.content.SharedPreferences

internal class NotificationPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("notificacao_prefs", Context.MODE_PRIVATE)

    fun savePeriod(startHour: Int, endHour: Int) {
        prefs.edit()
            .putInt("start_hour", startHour)
            .putInt("end_hour", endHour)
            .apply()
    }

    fun getPeriod(): Pair<Int, Int> {
        val start = prefs.getInt("start_hour", 9)
        val end = prefs.getInt("end_hour", 17)
        return start to end
    }
}
