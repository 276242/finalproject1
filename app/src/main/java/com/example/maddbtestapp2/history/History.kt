package com.example.maddbtestapp2.history

data class History(
    var id: Int? = null,
    var vaccineId: Int,
    var administeredDate: java.sql.Date
)