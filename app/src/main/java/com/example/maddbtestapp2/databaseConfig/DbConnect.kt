package com.example.maddbtestapp2.databaseConfig

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * DbConnect is a utility class that provides a method to establish a connection to a MySQL database.
 */
class DbConnect {
    companion object {
        val connectionUrl: String

        /**
         * Initializes the connection URL for the MySQL database.
         */
        init {
            val urlSB = StringBuilder("jdbc:mysql://")
            urlSB.append("sql11.freesqldatabase.com:3306/")
            urlSB.append("sql11701641?") // database name
            urlSB.append("useUnicode=true&characterEncoding=utf-8")
            urlSB.append("&user=sql11701641") // your user name
            urlSB.append("&password=uZ1riAXevy") // generate password
            urlSB.append("&serverTimezone=CET")
            connectionUrl = urlSB.toString()
        }

        /**
         * Establishes a connection to the MySQL database.
         * @return Connection object representing the database connection.
         * @throws RuntimeException if the connection fails or the JDBC driver cannot be loaded.
         */
        fun getConnection(): Connection {
            try {
                Class.forName("com.mysql.jdbc.Driver")
                val conn = DriverManager.getConnection(connectionUrl)
                println("Connected to the database")
                return conn
            } catch (e: SQLException) {
                e.printStackTrace()
                throw RuntimeException("Failed to establish database connection", e)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
                throw RuntimeException("Failed to load JDBC driver", e)
            }
        }
    }
}