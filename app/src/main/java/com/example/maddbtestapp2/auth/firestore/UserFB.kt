/**
 * UserFB is a class that represents a user in Firebase Firestore.
 *
 * @property id The unique identifier of the user.
 * @property name The name of the user.
 * @property email The email of the user.
 */
package com.example.maddbtestapp2.firestore

class UserFB(
    val id: String = "",
    val name: String = "",
    val email: String = ""

)