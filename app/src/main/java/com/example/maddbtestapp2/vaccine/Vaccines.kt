package com.example.maddbtestapp2.vaccine

import java.util.Date

data class Vaccines(
    var id: Int? = null,
    var vaccineName: String,
    var administeredDate: Date,
    var nextDoseDate: Date
)