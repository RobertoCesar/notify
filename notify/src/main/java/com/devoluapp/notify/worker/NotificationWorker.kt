package com.devoluapp.notify.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.devoluapp.notify.data.NotificationPreferences
import java.util.Calendar

class NotificationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        val prefs = NotificationPreferences(applicationContext)
        val (start, end) = prefs.getPeriod()

        val now = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (now in start..end) {
            NotificacaoUtils.showNotification(applicationContext, "Hora de se motivar!", "Você é incrível!")
        }

        return Result.success()
    }
}
