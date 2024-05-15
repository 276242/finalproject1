package com.example.maddbtestapp2.appointment

import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Time

class AppointmentQueries(private val connection: Connection) : AppointmentDAO {
    override fun getAppointmentById(id: Int): Appointment? {
        val query = "SELECT * FROM scheduled_vaccine_table WHERE schedule_id = ?"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, id)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                val vaccineId = resultSet.getInt("vaccine_id")
                val appointmentDate = resultSet.getDate("scheduled_date")
                val appointmentTime = resultSet.getTime("scheduled_time")
                Appointment(id, vaccineId, appointmentDate, appointmentTime)
            } else {
                null
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    override fun getAllAppointmentsByUserId(userId: Int): Set<Appointment?>? {
        val query = "SELECT * FROM scheduled_vaccine_table WHERE user_id = ?"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, userId)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            val appointments = mutableSetOf<Appointment>()
            while (resultSet.next()) {
                val id = resultSet.getInt("schedule_id")
                val vaccineId = resultSet.getInt("vaccine_id")
                val appointmentDate = resultSet.getDate("scheduled_date")
                val appointmentTime = resultSet.getTime("scheduled_time")
                Appointment(id, vaccineId, appointmentDate, appointmentTime)
            }
            if (appointments.isEmpty()) null else appointments
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    override fun getAllAppointmentsByVaccineId(vaccineId: Int): Set<Appointment?>? {
        val query = "SELECT * FROM scheduled_vaccine_table WHERE vaccine_id = ?"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, vaccineId)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            val appointments = mutableSetOf<Appointment>()
            while (resultSet.next()) {
                val id = resultSet.getInt("schedule_id")
                val vaccineId = resultSet.getInt("vaccine_id")
                val appointmentDate = resultSet.getDate("scheduled_date")
                val appointmentTime = resultSet.getTime("scheduled_time")
                Appointment(id, vaccineId, appointmentDate, appointmentTime)
            }
            if (appointments.isEmpty()) null else appointments
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    override fun getAllAppointments(): Set<Appointment?>? {
        val query = "SELECT * FROM scheduled_vaccine_table"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            val resultSet: ResultSet = preparedStatement.executeQuery()
            val appointments = mutableSetOf<Appointment>()
            while (resultSet.next()) {
                val id = resultSet.getInt("schedule_id")
                val vaccineId = resultSet.getInt("vaccine_id")
                val appointmentDate = resultSet.getDate("scheduled_date")
                val appointmentTime = resultSet.getTime("scheduled_time")
                appointments.add(Appointment(id, vaccineId, appointmentDate, appointmentTime))
            }
            if (appointments.isEmpty()) null else appointments
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    override fun insertAppointment(appointment: Appointment): Boolean {
        val query = "INSERT INTO scheduled_vaccine_table (vaccine_id, scheduled_date, scheduled_time) VALUES (?, ?, ?)"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, appointment.vaccineId ?: -1)
            preparedStatement.setDate(2, appointment.appointmentDate)
            preparedStatement.setTime(3, appointment.appointmentTime)
            val result = preparedStatement.executeUpdate()
            result > 0
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    override fun updateAppointment(id: Int, appointment: Appointment): Boolean {
        val query = "UPDATE scheduled_vaccine_table SET vaccine_id = ?, scheduled_date = ?, scheduled_time = ? WHERE schedule_id = ?"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, appointment.vaccineId ?: -1)
            preparedStatement.setDate(2, appointment.appointmentDate)
            preparedStatement.setTime(3, appointment.appointmentTime)
            preparedStatement.setInt(4, id)
            val result = preparedStatement.executeUpdate()
            result > 0
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    override fun deleteAppointment(id: Int): Boolean {
        val query = "DELETE FROM scheduled_vaccine_table WHERE schedule_id = ?"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, id)
            val result = preparedStatement.executeUpdate()
            result > 0
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }
}
