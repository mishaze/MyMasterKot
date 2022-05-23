package com.example.mymaster

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mymaster.presentations.reg.MainActivity


private const val CHANNEL_ID = "testId"
private const val CHANNEL_NAME = "testName"
private const val NOTIFICATION_ID = 1

class NotificationHelper {
    fun sendNotification(context: Context, title: String, body: String) {

        val manager = NotificationManagerCompat.from(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(createNotificationChannel())
        }


        //create a intent
        val contentIntent = Intent(context.applicationContext, MainActivity::class.java)
        //create pending intent
        val contentPendingIntent = PendingIntent.getActivity(
            context.applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(contentPendingIntent)
            .setAutoCancel(true)



        manager.notify(NOTIFICATION_ID, notificationBuilder.build())

    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        return NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
    }
}