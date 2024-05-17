//package com.example.maddbtestapp2.notifications
//
//import android.annotation.SuppressLint
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import com.example.maddbtestapp2.R
//
//class NotificationReceiver : BroadcastReceiver() {
//
//    @SuppressLint("MissingPermission")
//    override fun onReceive(context: Context?, intent: Intent?) {
//
//        val title = intent?.getStringExtra("title")
//        val message = intent?.getStringExtra("message")
//
//
//        val builder = NotificationCompat.Builder(context!!, "YourChannelId")
//            .setSmallIcon(R.drawable.ic_notification)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(context)) {
//            notify(123, builder.build())
//        }
//    }
//}
////
