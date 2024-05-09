package com.example.maddbtestapp2.vaccine

data class Vaccines(
    var id: Int? = null,
    var vaccineName: String,
    var administeredDate: java.sql.Date,
    var nextDoseDate: java.sql.Date
)