package com.l33pif.alarmmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationHelper {

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var notiBuilder : Notification.Builder
    private var channelId = "com.l33pif.alarmmanager"
    private var description = "test notification"

    fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, description: String) {

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notificationManager = context.getSystemService(NotificationManager::class.java)!!

            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            notiBuilder = Notification.Builder(context, channelId)
                .setContentTitle("l33pif")
                .setContentText("test notification")
                .setSmallIcon(R.drawable.ic_account_box)
                .setContentIntent(pendingIntent)
        }
    }


    fun createNotification(context: Context) {

        // create a group notification
        val groupBuilder = buildGroupNotification(context)

        // create the pet notification
        val notificationBuilder = buildNotificationForPet(context)

        // add an action to the pet notification
        val administerPendingIntent = createPendingIntentForAction(context)


        // call notify for both the group and the pet notification
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(0, groupBuilder.build())
        notificationManager.notify(1, notificationBuilder.build())
    }

    private fun buildGroupNotification(context: Context): NotificationCompat.Builder {
        val channelId = "${context.packageName}"
        return NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_account_box)
            setContentTitle("l33pif")
            setContentText("test notification")
            setAutoCancel(true)
            setGroupSummary(true)
        }
    }

    private fun buildNotificationForPet(context: Context): NotificationCompat.Builder {


        //val channelId = "${context.packageName}"

        return NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_account_box)
            setContentTitle("l33pif")
            setAutoCancel(true)

            // Launches the app to open the reminder edit screen when tapping the whole notification
            val intent = Intent(context, MainActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            setContentIntent(pendingIntent)
        }
    }

    private fun createPendingIntentForAction(context: Context): PendingIntent? {

        val administerIntent = Intent(context, Receiveer::class.java)

        return PendingIntent.getBroadcast(context, 209, administerIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

}

