package com.devoluapp.notify.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.Calendar

class HourlyNotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val prefs = context.getSharedPreferences("notify_prefs", Context.MODE_PRIVATE)
        val startHour = prefs.getInt("start_hour", 9)
        val endHour = prefs.getInt("end_hour", 17)

        val now = Calendar.getInstance()
        val currentHour = now.get(Calendar.HOUR_OF_DAY)

        if (currentHour in startHour..endHour) {
            showNotification("Hora de brilhar!", "Essa é sua notificação da hora cheia. 🚀")
        }

        return Result.success()
    }

    private fun showNotification(title: String, message: String) {
        val channelId = "notify_channel"
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Notificações de Hora em Hora",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setAutoCancel(true)
            .build()

        manager.notify((System.currentTimeMillis() % 10000).toInt(), notification)
    }
}
