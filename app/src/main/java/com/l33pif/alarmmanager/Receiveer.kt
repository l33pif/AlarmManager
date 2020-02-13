package com.l33pif.alarmmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import java.util.*
import com.l33pif.alarmmanager.MainActivity.*

class Receiveer: BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MainActiviy", "Receiver:  " + Date().toString())
        NotificationHelper.createNotification(context!!)

    }
}
