package com.example.maddbtestapp2.appointment

import java.sql.Date
import java.sql.Time

data class Appointment(
    var id: Int? = null,
    val vaccineId: Int?,
    val appointmentDate: Date,
    val appointmentTime: Time
)
