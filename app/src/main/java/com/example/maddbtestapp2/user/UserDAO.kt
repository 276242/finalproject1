package com.example.maddbtestapp2.user

interface UserDAO {
    fun getUserByUsername(username: String): User?
    fun getAllUsers(): Set<User?>?
    fun insertUser(user: User): Boolean
    fun updateUser(username: String, user: User): Boolean
    fun deleteUser(username: String): Boolean
}
