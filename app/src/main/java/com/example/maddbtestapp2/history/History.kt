package com.example.maddbtestapp2.history

import java.sql.Date

/**
 * Data class representing a history of administered vaccines.
 *
 * @property id Unique identifier of the history record. It's nullable and defaults to null.
 * @property vaccineId Identifier of the vaccine that was administered.
 * @property administeredDate Date when the vaccine was administered.
 */
data class History(
    var id: Int? = null,
    var vaccineId: Int,
    var administeredDate: Date
)