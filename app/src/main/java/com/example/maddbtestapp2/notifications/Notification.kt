package com.example.maddbtestapp2.notifications

import java.sql.Date
import java.sql.Time

data class Notification(
    var vaccineName: String,
    val notificationDate: Date,
    val notificationTime: Time
)
//