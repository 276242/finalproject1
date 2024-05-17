/**
 * Appointment is a data class that represents an appointment for a vaccine.
 *
 * @property id The unique identifier of the appointment.
 * @property vaccineId The identifier of the vaccine for which the appointment is scheduled.
 * @property appointmentDate The date of the appointment.
 * @property appointmentTime The time of the appointment.
 */
package com.example.maddbtestapp2.appointment

import java.sql.Date
import java.sql.Time

data class Appointment(
    var id: Int? = null,
    val vaccineId: Int?,
    val appointmentDate: Date,
    val appointmentTime: Time
)
