package com.example.maddbtestapp2.vaccine

import java.sql.Date

/**
 * Data class representing a vaccine history item.
 *
 * @property historyId Unique identifier of the history record. It's nullable and defaults to null.
 * @property vaccineName Name of the vaccine that was administered.
 * @property administrationDate Date when the vaccine was administered.
 */
data class VaccineHistoryItem(
    var historyId: Int? = null,
    var vaccineName: String,
    var administrationDate: Date
)