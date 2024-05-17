//package com.example.maddbtestapp2.notifications
//
//import android.app.DatePickerDialog
//import android.app.TimePickerDialog
//import android.os.Bundle
//import android.widget.Button
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.maddbtestapp2.R
//import com.example.maddbtestapp2.databaseConfig.DbConnect
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.sql.Date
//import java.sql.Time
//import java.util.Calendar
//import java.util.GregorianCalendar
//
//class NotificationActivity : AppCompatActivity() {
//
//    private lateinit var setDateButton: Button
//    private lateinit var setTimeButton: Button
//    private lateinit var btnSaveNotification: Button
//    private lateinit var selectedVaccineName: String
//
//    private lateinit var selectedNotifDate: Date
//    private var selectedNotifTime: String? = null
//
//    private var scheduleId: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_notification)
//
//        setDateButton = findViewById(R.id.setDateButton)
//        setTimeButton = findViewById(R.id.setTimeButton)
//        btnSaveNotification = findViewById(R.id.btnSaveNotification)
//
//        selectedVaccineName = intent.getStringExtra("selectedVaccineName").toString()
//        scheduleId = intent.getIntExtra("scheduleId", 0)
//
//        print("scheduleId: $scheduleId")
//
//        setDateButton.setOnClickListener {
//            showDatePickerDialog()
//        }
//
//        setTimeButton.setOnClickListener {
//            showTimePickerDialog()
////            selectedNotifTime = Time(12, 0, 0).time
//        }
//
//        btnSaveNotification.setOnClickListener {
//            saveNotification()
//        }
//
//
////        setDateButton.setOnClickListener {
////            val calendar = Calendar.getInstance()
////            val year = calendar.get(Calendar.YEAR)
////            val month = calendar.get(Calendar.MONTH)
////            val day = calendar.get(Calendar.DAY_OF_MONTH)
////
////            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
////                var selectedDate = GregorianCalendar(selectedYear, selectedMonth, selectedDayOfMonth).time
//////            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
////                selectedNotifDate = Date(selectedDate.time)
////
////            }, year, month, day).show()
////
////            print("selectedNotifDate: $selectedNotifDate")
////        }
//
////        setTimeButton.setOnClickListener {
////            val calendar = Calendar.getInstance()
////            val hour = calendar.get(Calendar.HOUR_OF_DAY)
////            val minute = calendar.get(Calendar.MINUTE)
////
////            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
////                val calendar = Calendar.getInstance()
////                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
////                calendar.set(Calendar.MINUTE, selectedMinute)
////                selectedNotifTime = Time.valueOf(String.format("%02d:%02d:00", selectedHour, selectedMinute))
////            }, hour, minute, true).show()
////
////            print("selectedNotifTime: $selectedNotifTime")
////        }
//
////        setTimeButton.setOnClickListener {
////            val calendar = Calendar.getInstance()
////            val hour = calendar.get(Calendar.HOUR_OF_DAY)
////            val minute = calendar.get(Calendar.MINUTE)
////
////            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
////                val calendar = Calendar.getInstance()
////                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
////                calendar.set(Calendar.MINUTE, selectedMinute)
////                selectedNotifTime = Time(calendar.timeInMillis)
////            }, hour, minute, true).show()
////        }
//
////        btnSaveNotification.setOnClickListener {
////            CoroutineScope(Dispatchers.IO).launch {
////                val connection = DbConnect.getConnection()
////                val notificationQueries = NotificationQueries(connection = connection)
////
////                val notification = Notification(
////                    notificationId = null,
////                    scheduleId = scheduleId,
////                    notificationDate = Date.valueOf(selectedNotifDate.toString()),
////                    notificationTime = Time.valueOf(selectedNotifTime.toString())
////                )
////
////                print("notification: $notification")
////
////                notificationQueries.insertNotification(notification)
////                connection.close()
////            }
////        }
//    }//
//
//    private fun showDatePickerDialog() {
//
//        val datePickerDialog = DatePickerDialog(
//            this,
//            { _, year, month, dayOfMonth ->
//                val calendar = Calendar.getInstance()
//                calendar.set(year, month, dayOfMonth, 0, 0, 0)
//                calendar.set(Calendar.MILLISECOND, 0)
//                selectedNotifDate = Date(calendar.timeInMillis)
//            },
//            Calendar.getInstance().get(Calendar.YEAR),
//            Calendar.getInstance().get(Calendar.MONTH),
//            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        )
//        datePickerDialog.show()
//
//    }
//
//    private fun showTimePickerDialog() {
//        val calendar = Calendar.getInstance()
//        val hour = calendar.get(Calendar.HOUR_OF_DAY)
//        val minute = calendar.get(Calendar.MINUTE)
//
//        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
//            val calendar = Calendar.getInstance()
//            calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
//            calendar.set(Calendar.MINUTE, selectedMinute)
//            calendar.set(Calendar.SECOND, 0)
//            selectedNotifTime = String.format("%02d:%02d:00", selectedHour, selectedMinute)
//        }, hour, minute, false).show()
//
//    }
//
//    private fun saveNotification() {
//        if (selectedNotifDate != null && selectedNotifTime != null) {
//            CoroutineScope(Dispatchers.IO).launch {
//                val connection = DbConnect.getConnection()
//                val notificationQueries = NotificationQueries(connection = connection)
//
//                val notification = Notification(
//                    notificationId = null,
//                    scheduleId = scheduleId,
//                    notificationDate = selectedNotifDate,
//                    notificationTime = Time.valueOf(selectedNotifTime))
//
//                notificationQueries.insertNotification(notification)
//                connection.close()
//            }
//            finish()
//        } else {
//        }
//    }
//}
//
