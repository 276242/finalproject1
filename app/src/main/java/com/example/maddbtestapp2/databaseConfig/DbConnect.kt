package com.example.maddbtestapp2.databaseConfig

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DbConnect {
    companion object {
        val connectionUrl: String

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

        fun getConnection(): Connection {
            try {
                // Load the JDBC driver
                Class.forName("com.mysql.jdbc.Driver")
                // Establish a connection
                val conn = DriverManager.getConnection(connectionUrl)
                // Test connection
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