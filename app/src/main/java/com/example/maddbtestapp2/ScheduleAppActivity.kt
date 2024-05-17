package com.example.maddbtestapp2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.maddbtestapp2.adapters.VaccinationAdapter
import com.example.maddbtestapp2.appointment.Appointment
import com.example.maddbtestapp2.appointment.AppointmentQueries
import com.example.maddbtestapp2.databaseConfig.DbConnect
import com.example.maddbtestapp2.vaccine.VaccinationActivity
import com.example.maddbtestapp2.vaccine.VaccinesQueries
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.sql.Time
import java.util.Calendar

class ScheduleAppActivity : AppCompatActivity() {

    private lateinit var vaccineSpinner: Spinner
    private lateinit var datePicker: CalendarView
    private lateinit var timePicker: TimePicker
    private lateinit var saveButton: Button
    var vaccines = mutableListOf<String>()

    private lateinit var selectedVaccineName: String
    private var appointmentDate: Long = 0
    private var appointmentTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_app)

        vaccineSpinner = findViewById(R.id.vaccPicker)
        datePicker = findViewById(R.id.datePickerSchd)
        timePicker = findViewById(R.id.timePickerSchd)
        saveButton = findViewById(R.id.btnSave2)
        val homeButton = findViewById<ImageView>(R.id.homeButton3)
        val scheduleButton = findViewById<ImageView>(R.id.scheduleButton3)


        CoroutineScope(Dispatchers.IO).launch {
            val connection = DbConnect.getConnection()
            val vaccinesQueries = VaccinesQueries(connection = connection)
            vaccines = (vaccinesQueries.getAllVaccineNames() ?: listOf()).toMutableList()
            connection.close()

            withContext(Dispatchers.Main) {
                vaccineSpinner.isEnabled = true

                val adapter = ArrayAdapter(
                    this@ScheduleAppActivity,
                    android.R.layout.simple_spinner_item,
                    vaccines
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                vaccineSpinner.adapter = adapter

                vaccineSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: android.view.View?,
                        position: Int,
                        id: Long
                    ) {
                        selectedVaccineName = vaccines[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // Handle nothing selected
                    }
                }
            }
        }

        datePicker.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            appointmentDate = calendar.timeInMillis
        }

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            appointmentTime = String.format("%02d:%02d:00", hourOfDay, minute)
        }

        saveButton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val connection = DbConnect.getConnection()
                val vaccinesQueries = VaccinesQueries(connection = connection)
                val appointmentQueries = AppointmentQueries(connection = connection)

                val vaccineId = vaccinesQueries.getVaccineIdByVaccineName(selectedVaccineName)
                val appointment = Appointment(
                    vaccineId = vaccineId,
                    appointmentDate = Date(appointmentDate),
                    appointmentTime = Time.valueOf(appointmentTime)
                )

                appointmentQueries.insertAppointment(appointment)

                connection.close()

                withContext(Dispatchers.Main) {
                    val intent = Intent(this@ScheduleAppActivity, MainActivity::class.java)
                    print("scheduleId: ${appointment.id.toString()}")
                    intent.putExtra("vaccineName", selectedVaccineName)
                    intent.putExtra("scheduleId", appointment.id)
                    startActivity(intent)
                    finish()
                }
            }
        }

        homeButton.setOnClickListener {
            goToMainActivity()
        }

        scheduleButton.setOnClickListener {
            goToScheduleActivity()
        }

    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToScheduleActivity() {
        val intent = Intent(this, ScheduleAppActivity::class.java)
        startActivity(intent)
        finish()
    }
}