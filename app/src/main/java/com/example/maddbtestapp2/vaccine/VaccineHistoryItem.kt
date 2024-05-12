package com.example.maddbtestapp2.vaccine

import java.sql.Date

data class VaccineHistoryItem(
    var historyId: Int? = null,
    var vaccineName: String,
    var administrationDate: Date
)
//
//