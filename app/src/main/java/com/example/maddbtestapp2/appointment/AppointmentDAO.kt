/**
 * AppointmentDAO is an interface that defines the operations for managing appointments.
 *
 * @property getAppointmentById Function to get an appointment by its id.
 * @property getAllAppointmentsByUserId Function to get all appointments for a specific user.
 * @property getAllAppointmentsByVaccineId Function to get all appointments for a specific vaccine.
 * @property getAllAppointments Function to get all appointments.
 * @property insertAppointment Function to insert a new appointment.
 * @property updateAppointment Function to update an existing appointment.
 * @property deleteAppointment Function to delete an appointment.
 */
package com.example.maddbtestapp2.appointment

interface AppointmentDAO {
    fun getAppointmentById(id: Int): Appointment?
    fun getAllAppointmentsByUserId(userId: Int): Set<Appointment?>?
    fun getAllAppointmentsByVaccineId(vaccineId: Int): Set<Appointment?>?
    fun getAllAppointments(): Set<Appointment?>?
    fun insertAppointment(appointment: Appointment) : Boolean
    fun updateAppointment(id: Int, appointment: Appointment) : Boolean
    fun deleteAppointment(id: Int) : Boolean
}
