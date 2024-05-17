package com.example.maddbtestapp2.history

import java.sql.Date

interface HistoryDAO {
    fun getHistoryById(id: Int): History?
    fun getHistoryByVaccineId(vaccineId: Int): List<History>?
    fun getHistoryIdByVaccineId(vaccineId: Int): Int
    fun getDateAdministeredByVaccineId(vaccineId: Int): Date?
    fun getAllHistories(): Set<History?>?
    fun insertHistory(history: History): Boolean
    fun updateAdministrationDate(id: Int, newDate: Date): Boolean
    fun updateHistory(id: Int, history: History): Boolean
    fun deleteHistory(id: Int): Boolean
}
