package com.example.maddbtestapp2.appointment

import java.sql.Date
import java.sql.Time

data class Appointment(
    var id: Int? = null,
    val userId: Int? = null,
    val vaccineId: Int?,
    val date: Date,
    val time: Time
)
