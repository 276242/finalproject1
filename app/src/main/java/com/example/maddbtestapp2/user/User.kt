package com.example.maddbtestapp2.user

/**
 * Data class representing a user.
 *
 * @property user_id Unique identifier of the user.
 * @property name Name of the user.
 * @property email Email of the user.
 */
data class User(
    val user_id: Int,
    val name: String,
    val email: String
)