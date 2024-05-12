package com.example.maddbtestapp2.user//package com.example.maddbtestapp2
//
//
//import com.example.myapplicationmad.firestore.User
//import java.sql.Connection
//import java.sql.ResultSet
//
//class UserQueries(private val connection: Connection) : UserDAO {
//    override fun getUserByUsername(username: String): User? {
//        val query = "SELECT * FROM user_table WHERE username = ?"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setString(1, username)
//        val resultSet = preparedStatement.executeQuery()
//
//        return if (resultSet.next()) {
//            mapResultSetToUser(resultSet)
//        } else {
//            null
//        }
//    }
//
//    override fun getAllUsers(): Set<User?>? {
//        val query = "SELECT * FROM user_table"
//        val preparedStatement = connection.prepareStatement(query)
//        val resultSet = preparedStatement.executeQuery()
//        val users = mutableSetOf<User?>()
//        while (resultSet.next()) {
//            users.add(mapResultSetToUser(resultSet))
//        }
//        return if (users.isEmpty()) null else users
//    }
//
//    override fun insertUser(user: User): Boolean {
//        val query = "INSERT INTO user_table (username, password, email) VALUES (?, ?, ?)"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setString(1, user.username)
//        preparedStatement.setString(2, user.password)
//        preparedStatement.setString(3, user.email)
//        return preparedStatement.executeUpdate() > 0
//    }
//
//    override fun updateUser(username: String, user: User): Boolean {
//        val query = "UPDATE user_table SET password = ?, email = ? WHERE username = ?"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setString(1, user.password)
//        preparedStatement.setString(2, user.email)
//        preparedStatement.setString(3, username)
//        return preparedStatement.executeUpdate() > 0
//    }
//
//    override fun deleteUser(username: String): Boolean {
//        val query = "DELETE FROM user_table WHERE username = ?"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setString(1, username)
//        return preparedStatement.executeUpdate() > 0
//    }
//
//    private fun mapResultSetToUser(resultSet: ResultSet): User {
//        return User(
//            username = resultSet.getString("username"),
//            password = resultSet.getString("password"),
//            email = resultSet.getString("email")
//        )
//    }
//}
