package com.example.maddbtestapp2

import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.Connection

class HistoryQueries(private val connection : Connection) : HistoryDAO {
    override fun getHistoryById(id: Int): History? {
        val query = "SELECT * FROM history_table WHERE history_id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, id)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return History(
                id = resultSet.getInt("history_id"),
                vaccineId = resultSet.getInt("vaccine_id"),
                administeredDate = resultSet.getDate("date_administered"),
            )
        }
        return null
    }

    override fun getHistoryByVaccineId(vaccineId: Int): History? {
        val query = "SELECT * FROM history_table WHERE vaccine_id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, vaccineId)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return History(
                id = resultSet.getInt("history_id"),
                vaccineId = resultSet.getInt("vaccine_id"),
                administeredDate = resultSet.getDate("date_administered"),
            )
        }

        return null
    }


    override fun getHistoryIdByVaccineId(vaccineId: Int): Int {
        val query = "SELECT `history_id` FROM `history_table` WHERE `vaccine_id` = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, vaccineId)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return resultSet.getInt("history_id")
        }
        return -1
    }

    override fun getDateAdministeredByVaccineId(vaccineId: Int): java.sql.Date? {
        val query = "SELECT `date_administered` FROM `history_table` WHERE `vaccine_id` = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, vaccineId)
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return resultSet.getDate("date_administered")
        }
        return null
    }

    override fun getAllHistories(): Set<History?>? {
        val query = "SELECT * FROM history_table"
        val preparedStatement = connection.prepareStatement(query)
        val resultSet = preparedStatement.executeQuery()
        val histories = mutableSetOf<History>()
        while (resultSet.next()) {
            histories.add(
                History(
                    id = resultSet.getInt("history_id"),
                    vaccineId = resultSet.getInt("vaccine_id"),
                    administeredDate = resultSet.getDate("date_administered"),
                )
            )
        }
        return histories
    }

    override fun insertHistory(history: History): Boolean {
        val query = "INSERT INTO history_table (vaccine_id, date_administered) VALUES (?, ?)"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, history.vaccineId)
        preparedStatement.setDate(2, history.administeredDate)
        return preparedStatement.executeUpdate() > 0
    }

    override fun updateHistory(id: Int, history: History): Boolean {
        val query = "UPDATE history_table SET vaccine_id = ?, date_administered = ? WHERE history_id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, history.vaccineId)
        preparedStatement.setDate(2, history.administeredDate)
        preparedStatement.setInt(3, id)
        return preparedStatement.executeUpdate() > 0
    }

    override fun deleteHistory(id: Int): Boolean {
        val query = "DELETE FROM history_table WHERE history_id = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, id)
        return preparedStatement.executeUpdate() > 0
    }
}