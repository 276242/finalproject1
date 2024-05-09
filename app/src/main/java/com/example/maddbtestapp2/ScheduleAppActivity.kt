package com.example.maddbtestapp2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TimePicker
//import com.example.maddbtestapp2.notifications.NotificationHelper
import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.Date
import java.util.Calendar

class ScheduleAppActivity : BaseActivity() {

    private lateinit var vaccineSpinner: Spinner
    private lateinit var datePicker: CalendarView
    private lateinit var timePicker: TimePicker
    private lateinit var saveButton: Button
//    private lateinit var notificationHelper: NotificationHelper

    private var selectedVaccine: Vaccines? = null
    private var selectedDate: Long = 0
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_app)

        vaccineSpinner = findViewById(R.id.vaccPicker)
        datePicker = findViewById(R.id.datePickerSchd)
        timePicker = findViewById(R.id.timePickerSchd)
        saveButton = findViewById(R.id.btnSave2)
//        notificationHelper = NotificationHelper(applicationContext) // Initialize with application context



        // TODO: Replace with actual list of vaccines
        val date1 = java.sql.Date.valueOf("2024-06-06")
        val date2 = java.sql.Date.valueOf("2024-08-06")
        val date3 = java.sql.Date.valueOf("2024-12-04")
        val date4 = java.sql.Date.valueOf("2024-12-07")

        val vaccines = listOf(
            Vaccines(1, "Vaccine 1", date1, date2),
            Vaccines(2, "Vaccine 2", date3, date4)
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, vaccines.map { it.vaccineName })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        vaccineSpinner.adapter = adapter

        vaccineSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedVaccine = vaccines[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedVaccine = null
            }
        }

        datePicker.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            selectedDate = calendar.timeInMillis
        }

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        }

        saveButton.setOnClickListener {
            if (selectedVaccine != null && selectedDate != 0L && selectedTime.isNotEmpty()) {
//                notificationHelper.scheduleNotification(selectedVaccine!!, selectedDate, selectedTime)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}