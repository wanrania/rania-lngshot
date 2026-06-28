package com.example.rania_lngshot.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.rania_lngshot.home.agenda_desa.AgendaDesaActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        val title =
            intent.getStringExtra("title") ?: "Pengingat"

        val message =
            intent.getStringExtra("message") ?: "Waktu Agenda"

        val targetClassName =
            intent.getStringExtra("target_activity")

        val targetIntent =

            if (!targetClassName.isNullOrEmpty()) {

                val clazz = Class.forName(targetClassName)

                Intent(
                    context,
                    clazz
                ).apply {

                    flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TOP

                }

            } else {

                Intent(
                    context,
                    AgendaDesaActivity::class.java
                )

            }

        NotificationHelper.showNotification(

            context = context,

            title = title,

            message = message,

            intent = targetIntent

        )

    }

}