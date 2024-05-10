package com.example.maddbtestapp2.databaseConfig

import java.sql.DriverManager
import java.sql.SQLException


class DbConnect {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        // Set up connection details
        val urlSB = StringBuilder("jdbc:mysql://")
        urlSB.append("sql11.freesqldatabase.com:3306/")
        urlSB.append("sql11701641") // database name
        urlSB.append("useUnicode=true&characterEncoding=utf-8")
        urlSB.append("&user=sql11701641") // your user name
        urlSB.append("&password=uZ1riAXevy") // generate password
        urlSB.append("&serverTimezone=CET")
        val connectionUrl = urlSB.toString()

        try {
            // Establish a connection
            val conn = DriverManager.getConnection(connectionUrl)

            // Close the connection
            conn.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}
//


//    // Database connection details
//    private const val URL = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11701641?useUnicode=true&characterEncoding=utf-8&serverTimezone=CET"
//    private const val USER = "sql11701641"
//    private const val PASS = "uZ1riAXevy"
//
//    // Function to get a connection to the database
//    fun getConnection(): Connection {
//        return DriverManager.getConnection(URL, USER, PASS)
//    }
//
//    companion object {
//        // Main function to test the database connection
//        @JvmStatic
//        fun main(args: Array<String>) {
//            try {
//                // Getting a connection
//                val conn = dbConnect().getConnection()
//                // Closing the connection
//                conn.close()
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//        }
//    }
}
