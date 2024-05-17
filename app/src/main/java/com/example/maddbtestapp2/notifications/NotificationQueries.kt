//package com.example.maddbtestapp2.notifications
//
//import java.sql.Connection
//
//class NotificationQueries(private val connection: Connection) : NotificationsDAO {
//
//    override fun getNotificationById(notificationId: Int): Notification? {
//        val query = "SELECT * FROM notification_table WHERE notification_id = ?"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setInt(1, notificationId)
//        val resultSet = preparedStatement.executeQuery()
//        if (resultSet.next()) {
//            return Notification(
//                notificationId = resultSet.getInt("notification_id"),
//                scheduleId = resultSet.getInt("schedule_id"),
//                notificationDate = resultSet.getDate("notification_date"),
//                notificationTime = resultSet.getTime("notification_time")
//            )
//        }
//        return null
//    }
//
//    override fun insertNotification(notification: Notification): Boolean {
//        val query = "INSERT INTO notification_table (schedule_id, notification_date, notification_time) VALUES (?, ?, ?)"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setInt(1, notification.scheduleId)
//        preparedStatement.setDate(2, notification.notificationDate)
//        preparedStatement.setTime(3, notification.notificationTime)
//        return preparedStatement.executeUpdate() > 0
//    }
//
//    override fun updateNotification(notificationId: Int, notification: Notification): Boolean {
//        val query = "UPDATE notification_table SET schedule_id = ?, notification_date = ?, notification_time = ? WHERE notification_id = ?"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setInt(1, notification.scheduleId)
//        preparedStatement.setDate(2, notification.notificationDate)
//        preparedStatement.setTime(3, notification.notificationTime)
//        preparedStatement.setInt(4, notificationId)
//        return preparedStatement.executeUpdate() > 0
//    }
//
//    override fun deleteNotification(notificationId: Int): Boolean {
//        val query = "DELETE FROM notification_table WHERE notification_id = ?"
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setInt(1, notificationId)
//        return preparedStatement.executeUpdate() > 0
//    }
//}