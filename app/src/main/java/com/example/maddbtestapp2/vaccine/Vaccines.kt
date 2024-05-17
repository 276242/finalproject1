package com.example.maddbtestapp2.vaccine

import java.sql.Date

/**
 * Data class representing a vaccine.
 *
 * @property id Unique identifier of the vaccine. It's nullable and defaults to null.
 * @property vaccineName Name of the vaccine.
 * @property administeredDate Date when the vaccine was administered.
 * @property nextDoseDate Date when the next dose of the vaccine is due.
 */
data class Vaccines(
    var id: Int? = null,
    var vaccineName: String,
    var administeredDate: Date,
    var nextDoseDate: Date
)