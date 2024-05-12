package com.example.maddbtestapp2.appointment

import com.example.maddbtestapp2.vaccine.Vaccines
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
                val userId = resultSet.getInt("user_id")
                val vaccineId = resultSet.getInt("vaccine_id")
                val date = resultSet.getDate("scheduled_date")
                val time = resultSet.getTime("scheduled_time")
                Appointment(id, userId, vaccineId, date, time)
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
                val date = resultSet.getDate("scheduled_date")
                val time = resultSet.getTime("scheduled_time")
                appointments.add(Appointment(id, userId, vaccineId, date, time))
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
                val userId = resultSet.getInt("user_id")
                val date = resultSet.getDate("scheduled_date")
                val time = resultSet.getTime("scheduled_time")
                appointments.add(Appointment(id, userId, vaccineId, date, time))
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
                val userId = resultSet.getInt("user_id")
                val vaccineId = resultSet.getInt("vaccine_id")
                val date = resultSet.getDate("scheduled_date")
                val time = resultSet.getTime("scheduled_time")
                appointments.add(Appointment(id, userId, vaccineId, date, time))
            }
            if (appointments.isEmpty()) null else appointments
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    override fun insertAppointment(appointment: Appointment): Boolean {
        val query = "INSERT INTO schedule_appointment (user_id, vaccine_id, scheduled_date, scheduled_time) VALUES (?, ?, ?, ?)"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            appointment.userId?.let { preparedStatement.setInt(1, it) }
            appointment.vaccineId?.let { preparedStatement.setInt(2, it) }
            preparedStatement.setDate(3, appointment.date)
            preparedStatement.setTime(4, appointment.time)
            val result = preparedStatement.executeUpdate()
            result > 0
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    override fun updateAppointment(id: Int, appointment: Appointment): Boolean {
        val query = "UPDATE scheduled_vaccine_table SET user_id = ?, vaccine_id = ?, scheduled_date = ?, scheduled_time = ? WHERE schedule_id = ?"
        return try {
            val preparedStatement: PreparedStatement = connection.prepareStatement(query)
            appointment.userId?.let { preparedStatement.setInt(1, it) }
            appointment.vaccineId?.let { preparedStatement.setInt(2, it) }
            preparedStatement.setDate(3, appointment.date)
            preparedStatement.setTime(4, appointment.time)
            preparedStatement.setInt(5, id)
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
