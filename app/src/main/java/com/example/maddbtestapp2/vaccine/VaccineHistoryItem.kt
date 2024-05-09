package com.example.maddbtestapp2.vaccine

import java.sql.Date

data class VaccineHistoryItem(
    val vaccineName: String,
    val doseNumber: Int,
    var administrationDate: Date

)