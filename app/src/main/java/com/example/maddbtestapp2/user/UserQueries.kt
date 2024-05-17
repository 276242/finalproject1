package com.example.maddbtestapp2.user

import java.sql.Connection
import java.sql.ResultSet

class UserQueries(private val connection: Connection) : UserDAO {

    /**
     * UserQueries is a class that implements the UserDAO interface. It provides the functionality for managing users in the database.
     * This includes operations such as retrieving a user by their username, retrieving all users, inserting a new user, updating an existing user, and deleting a user.
     * These operations are performed using SQL queries executed on a provided Connection object.
     * The results of these queries are processed and returned in a form that can be easily used by other parts of the application.
     */
    override fun getUserByUsername(username: String): User? {
        val query = "SELECT * FROM user_table WHERE username = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, username)
        val resultSet = preparedStatement.executeQuery()

        return if (resultSet.next()) {
            mapResultSetToUser(resultSet)
        } else {
            null
        }
    }

    /**
     * Function to get all users.
     */
    override fun getAllUsers(): Set<User>? {
        val query = "SELECT * FROM user_table"
        val preparedStatement = connection.prepareStatement(query)
        val resultSet = preparedStatement.executeQuery()
        val users = mutableSetOf<User>()
        while (resultSet.next()) {
            users.add(mapResultSetToUser(resultSet))
        }
        return if (users.isEmpty()) null else users
    }

    /**
     * Function to insert a new user.
     */
    override fun insertUser(user: User): Boolean {
        val query = "INSERT INTO user_table (user_id, name, email) VALUES (?, ?, ?)"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setInt(1, user.user_id)
        preparedStatement.setString(2, user.name)
        preparedStatement.setString(3, user.email)
        return preparedStatement.executeUpdate() > 0
    }

    /**
     * Function to update an existing user.
     */
    override fun updateUser(username: String, user: User): Boolean {
        val query = "UPDATE user_table SET name = ?, email = ? WHERE username = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, user.name)
        preparedStatement.setString(2, user.email)
        preparedStatement.setString(3, username)
        return preparedStatement.executeUpdate() > 0
    }

    /**
     * Function to delete a user.
     */
    override fun deleteUser(username: String): Boolean {
        val query = "DELETE FROM user_table WHERE username = ?"
        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, username)
        return preparedStatement.executeUpdate() > 0
    }

    /**
     * Function to map a ResultSet to a User object.
     */
    private fun mapResultSetToUser(resultSet: ResultSet): User {
        return User(
            user_id = resultSet.getInt("user_id"),
            name = resultSet.getString("name"),
            email = resultSet.getString("email")
        )
    }
}