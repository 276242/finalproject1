package com.example.maddbtestapp2

class HistoryDAO {
    fun getHistoryById(id: Int): History? {
        return null
    }

    fun getHistoryByVaccineName(vaccineName: String): History? {
        return null
    }

    fun doesHistoryExist(vaccineName: String): Boolean {
        return false
    }

    fun getHistoryIdByVaccineName(vaccineName: String): Int {
        return 0
    }

    fun getDateAdministeredByVaccineName(id: Int): Date? {
        return null
    }

    fun getAllHistories(): Set<History?>? {
        return null
    }

    fun insertHistory(history: History) : Boolean {
        return false
    }

    fun updateHistory(id: Int, history: History) : Boolean {
        return false
    }

    fun deleteHistory(id: Int) : Boolean {
        return false
    }
}