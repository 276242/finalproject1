package com.example.maddbtestapp2.notifications

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.databaseConfig.DbConnect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Time
import java.util.Calendar
import java.util.GregorianCalendar

class NotificationActivity : AppCompatActivity() {

    private lateinit var setDateButton: Button
    private lateinit var setTimeButton: Button
    private lateinit var btnSaveNotification: Button
    private lateinit var selectedVaccineName: String

    private lateinit var selectedNotifDate: Date
    private lateinit var selectedNotifTime: Time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setDateButton = findViewById(R.id.setDateButton)
        setTimeButton = findViewById(R.id.setTimeButton)
        btnSaveNotification = findViewById(R.id.btnSaveNotification)

        selectedVaccineName = intent.getStringExtra("selectedVaccineName").toString()
        var scheduleId = intent.getIntExtra("scheduleId", 0)

        setDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                var selectedDate = GregorianCalendar(selectedYear, selectedMonth, selectedDayOfMonth).time
//            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                selectedNotifDate = Date(selectedDate.time)

            }, year, month, day).show()
        }

        setTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d:00", selectedHour, selectedMinute)
                selectedNotifTime = Time.valueOf(selectedTime)
            }, hour, minute, true).show()
        }

        btnSaveNotification.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val connection = DbConnect.getConnection()
                val notificationQueries = NotificationQueries(connection = connection)

                val notification = Notification(
                    notificationId = null,
                    scheduleId = scheduleId,
                    notificationDate = Date.valueOf(selectedNotifDate.time.toString()),
                    notificationTime = Time.valueOf(selectedNotifTime.time.toString())
                )

                notificationQueries.insertNotification(notification)
                connection.close()
            }

        }
    }

//    private fun showDatePickerDialog() {
//
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
//            var selectedDate = GregorianCalendar(selectedYear, selectedMonth, selectedDayOfMonth).time
////            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
//            var selectedNotifDate = Date(selectedDate.time)
//
//        }, year, month, day).show()
//
//    }

//    private fun showTimePickerDialog() {
//        val calendar = Calendar.getInstance()
//        val hour = calendar.get(Calendar.HOUR_OF_DAY)
//        val minute = calendar.get(Calendar.MINUTE)
//
//    }
//
//    private fun saveNotification(date: String, time: String) {
//        val vaccineName = vaccineNameEditText.text.toString().trim()
//
//        showToast("Notification scheduled for $date at $time for $vaccineName")
//    }

//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
}
