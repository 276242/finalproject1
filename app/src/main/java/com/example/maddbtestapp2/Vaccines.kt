package com.example.maddbtestapp2

import java.util.Date

data class Vaccines (
    var id: Int? = null,
    var vaccineName: String,
    var administeredDate: Date,
    var nextDoseDate: Date
)