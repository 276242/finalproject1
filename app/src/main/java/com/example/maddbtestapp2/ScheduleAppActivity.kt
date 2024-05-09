package com.example.maddbtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TimePicker
import java.util.Calendar
import java.util.Date

class ScheduleAppActivity : BaseActivity() {

    private lateinit var vaccineSpinner: Spinner
    private lateinit var datePicker: CalendarView
    private lateinit var timePicker: TimePicker
    private lateinit var saveButton: Button

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

        // TODO: Replace with actual list of vaccines
        val vaccines = listOf(Vaccines(1, "Vaccine 1", Date(), Date()), Vaccines(2, "Vaccine 2", Date(), Date()))
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
            // TODO: Store the selected vaccine, date, and time for later use
            // For example, you could save them in a database or send them to another activity
            println("Selected vaccine: ${selectedVaccine?.vaccineName}")
            println("Selected date: $selectedDate")
            println("Selected time: $selectedTime")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}