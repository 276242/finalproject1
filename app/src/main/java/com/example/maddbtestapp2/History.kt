package com.example.maddbtestapp2

data class History(
    var id: Int? = null,
    var vaccineId: Int,
    var administeredDate: java.sql.Date
)