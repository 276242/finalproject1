package com.example.maddbtestapp2

import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.Connection

class VaccinesQueries(private val connection : Connection) : VaccinesDAO {
    override fun getVaccineById(id: Int): Vaccines? {
           val query = "SELECT * FROM `vaccine_table` WHERE `vaccine_id` = ?"
            val preparedStatement = connection.prepareStatement(query)
            preparedStatement.setInt(1, id)
            val resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return Vaccines(
                    id = resultSet.getInt("vaccine_id"),
                    vaccineName = resultSet.getString("vaccine_name"),
                    administeredDate = resultSet.getDate("date_administered"),
                    nextDoseDate = resultSet.getDate("date_next_dose")
                )
            }
            return null
    }

    override fun getVaccineByName(vaccineName: String): Vaccines? {
        val query = "SELECT * FROM `vaccine_table` WHERE `vaccine_name` = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, vaccineName)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return Vaccines(
                id = resultSet.getInt("vaccine_id"),
                vaccineName = resultSet.getString("vaccine_name"),
                administeredDate = resultSet.getDate("date_administered"),
                nextDoseDate = resultSet.getDate("date_next_dose")
            )
        }
        return null
    }

    override fun doesVaccineExist(vaccineName: String): Boolean {
        val query = "SELECT * FROM `vaccine_table` WHERE `vaccine_name` = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, vaccineName)
        val resultSet = preparedStatement.executeQuery()
        return resultSet.next()
    }

    override fun getVaccineIdByVaccineName(vaccineName: String): Int {
        val query = "SELECT `vaccine_id` FROM `vaccine_table` WHERE `vaccine_name` = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, vaccineName)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return resultSet.getInt("vaccine_id")
        }
        return -1
    }

    override fun getAllVaccines(): Set<Vaccines?>? {
        val query = "SELECT * FROM `vaccine_table`"
        val preparedStatement = connection.prepareStatement(query)
        val resultSet = preparedStatement.executeQuery()
        val vaccines = mutableSetOf<Vaccines>()
        while (resultSet.next()) {
            vaccines.add(
                Vaccines(
                    id = resultSet.getInt("vaccine_id"),
                    vaccineName = resultSet.getString("vaccine_name"),
                    administeredDate = resultSet.getDate("date_administered"),
                    nextDoseDate = resultSet.getDate("date_next_dose")
                )
            )
        }
        return if (vaccines.isEmpty()) {
            null
        } else {
            vaccines
        }
    }

    override fun insertVaccine(vaccine: Vaccines): Boolean {
        val query = "INSERT INTO `vaccine_table` (`vaccine_name`, `date_administered`, `date_next_dose`) VALUES (?, ?, ?)"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, vaccine.vaccineName)
        preparedStatement.setDate(2, vaccine.administeredDate)
        preparedStatement.setDate(3, vaccine.nextDoseDate)
        return preparedStatement.executeUpdate() > 0
    }

    override fun updateVaccine(id: Int, vaccine: Vaccines): Boolean {
        val query = "UPDATE `vaccine_table` SET `date_administered = ? WHERE `vaccine_name = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setDate(1, vaccine.administeredDate)
        preparedStatement.setString(2, vaccine.vaccineName)
        return preparedStatement.executeUpdate() > 0
    }

    override fun deleteVaccine(id: Int): Boolean {
        val query = "DELETE FROM `vaccine_table` WHERE 'vaccine_id' = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, id)
        return preparedStatement.executeUpdate() > 0
    }

}