package com.example.maddbtestapp2.appointment

import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.Date

interface AppointmentDAO {
    fun getAppointmentById(id: Int): Appointment?
    fun getAllAppointmentsByUserId(userId: Int): Set<Appointment?>?
    fun getAllAppointmentsByVaccineId(vaccineId: Int): Set<Appointment?>?
    fun getAllAppointments(): Set<Appointment?>?
    fun insertAppointment(appointment: Appointment) : Boolean
    fun updateAppointment(id: Int, appointment: Appointment) : Boolean
    fun deleteAppointment(id: Int) : Boolean
}
