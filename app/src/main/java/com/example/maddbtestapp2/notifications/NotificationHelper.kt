package com.example.maddbtestapp2.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.vaccine.Vaccines
import java.util.Calendar

class NotificationHelper(private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "VACCINATION_REMINDER_CHANNEL"
        private const val NOTIFICATION_ID = 100
    }

    fun scheduleNotification(vaccine: Vaccines, dateInMillis: Long, time: String) {
        val title = "Vaccination Reminder"
        val message = "Don't forget your ${vaccine.vaccineName} appointment at $time on ${formatDate(dateInMillis)}"

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("title", title)
        intent.putExtra("message", message)
        val pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE)

        val calendar = Calendar.getInstance().apply {
            timeInMillis = dateInMillis
            set(Calendar.HOUR_OF_DAY, time.substringBefore(":").toInt())
            set(Calendar.MINUTE, time.substringAfter(":").toInt())
            set(Calendar.SECOND, 0)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun formatDate(dateInMillis: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = dateInMillis
        return "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)}"
    }
}
//