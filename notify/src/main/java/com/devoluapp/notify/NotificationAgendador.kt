package com.devoluapp.notify

import android.content.Context
import androidx.work.*
import com.devoluapp.notify.data.NotificationPreferences
import com.devoluapp.notify.worker.NotificationWorker
import java.util.concurrent.TimeUnit

object NotificacaoAgendador {
    fun configurar(context: Context, horaInicial: Int, horaFinal: Int) {
        // salva as horas
        val prefs = NotificationPreferences(context)
        prefs.savePeriod(horaInicial, horaFinal)

        // agenda o worker
        val request = PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.HOURS)
            .setInitialDelay(1, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "notificacao_horaria",
            ExistingPeriodicWorkPolicy.UPDATE,
            request
        )
    }
}
