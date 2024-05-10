package com.example.maddbtestapp2

import java.sql.Date

interface HistoryDAO {
    fun getHistoryById(id: Int): History?
    fun getHistoryByVaccineId(vaccineId: Int): History?
    fun getHistoryIdByVaccineId(vaccineId: Int): Int
    fun getDateAdministeredByVaccineId(vaccineId: Int): Date?
    fun getAllHistories(): Set<History?>?
    fun insertHistory(history: History): Boolean
    fun updateHistory(id: Int, history: History): Boolean
    fun deleteHistory(id: Int): Boolean
}