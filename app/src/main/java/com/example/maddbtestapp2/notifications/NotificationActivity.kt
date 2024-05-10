package com.example.maddbtestapp2.notifications

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.maddbtestapp2.R
import java.util.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var setDateButton: Button
    private lateinit var setTimeButton: Button
    private lateinit var btnSaveNotification: Button
    private lateinit var vaccineNameEditText: EditText

    private var selectedDate: String? = null
    private var selectedTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setDateButton = findViewById(R.id.setDateButton)
        setTimeButton = findViewById(R.id.setTimeButton)
        btnSaveNotification = findViewById(R.id.btnSaveNotification)
        vaccineNameEditText = findViewById(R.id.vaccineNameEditText)

        setDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        setTimeButton.setOnClickListener {
            showTimePickerDialog()
        }

        btnSaveNotification.setOnClickListener {
            if (selectedDate != null && selectedTime != null) {
                saveNotification(selectedDate!!, selectedTime!!)
            } else {
                showToast("Please select both date and time.")
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)


    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

    }

    private fun saveNotification(date: String, time: String) {
        val vaccineName = vaccineNameEditText.text.toString().trim()

        showToast("Notification scheduled for $date at $time for $vaccineName")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
