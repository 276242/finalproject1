package com.example.maddbtestapp2.vaccine

import java.util.Date

data class VaccineHistoryItem(
    val vaccineName: String,
    val doseNumber: Int,
    val administrationDate: Date
)