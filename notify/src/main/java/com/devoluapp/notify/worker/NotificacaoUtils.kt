package com.devoluapp.notify.worker

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificacaoUtils {
    fun showNotification(context: Context, title: String, message: String) {
        val channelId = "motivacao"
        val channel = NotificationChannel(
            channelId,
            "Mensagens motivacionais",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("Notify", "showNotification: permissão POST_NOTIFICATIONS não concedida. "+
                  "Consider calling ActivityCompat#requestPermissions here to request the missing "+
                  "permissions, and then overriding public void onRequestPermissionsResult(int requ"+
                  "estCode, String[] permissions, int[] grantResults) to handle the case where the "+
                  "user grants the permission. See the documentation for ActivityCompat"+
                  "#requestPermissions for more details.")
            return
        }
        NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), builder.build())
        return
    }
}
