package com.example.maddbtestapp2.notifications

import java.sql.Date
import java.sql.Time

data class Notification(
    var notificationId: Int,
    var userId: Int,
    var scheduleId: Int,
    val notificationDate: Date,
    val notificationTime: Time
)
//