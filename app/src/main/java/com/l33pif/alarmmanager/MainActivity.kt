package com.l33pif.alarmmanager

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import com.l33pif.alarmmanager.Receiveer.*

class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var alarmmanager: AlarmManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        alarmmanager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        NotificationHelper.createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_HIGH, true,"Notification channel test")



        created_btn.setOnClickListener {
            val seconds = edt_timer.text.toString().toInt() * 1000
            val intent = Intent(context, Receiveer::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", "Create : " + Date().toString())
            alarmmanager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, pendingIntent)
        }

        update_btn.setOnClickListener {
            val seconds = edt_timer.text.toString().toInt() * 1000
            val intent = Intent(context, Receiveer::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", "Update : " + Date().toString())
            alarmmanager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, pendingIntent)
        }

        cancel_btn.setOnClickListener {
            val intent = Intent(context, Receiveer::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", "Cancel : " + Date().toString())
            alarmmanager.cancel(pendingIntent)
        }
    }


}
