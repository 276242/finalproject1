/**
 * UserDAO is an interface that defines the operations for managing users in the database.
 *
 * @property getUserByUsername Function to get a user by their username.
 * @property getAllUsers Function to get all users.
 * @property insertUser Function to insert a new user.
 * @property updateUser Function to update an existing user.
 * @property deleteUser Function to delete a user.
 */
package com.example.maddbtestapp2.user

interface UserDAO {
    fun getUserByUsername(username: String): User?
    fun getAllUsers(): Set<User?>?
    fun insertUser(user: User): Boolean
    fun updateUser(username: String, user: User): Boolean
    fun deleteUser(username: String): Boolean
}