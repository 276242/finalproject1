package com.example.maddbtestapp2.notifications

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.maddbtestapp2.R

class NotificationReceiver : BroadcastReceiver() {

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        // Retrieve notification details from the intent extras
        val title = intent?.getStringExtra("title")
        val message = intent?.getStringExtra("message")

        // Create a notification
        val builder = NotificationCompat.Builder(context!!, "YourChannelId")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Show the notification
        with(NotificationManagerCompat.from(context)) {
            notify(123, builder.build())
        }
    }
}
//
