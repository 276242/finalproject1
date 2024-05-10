package com.example.maddbtestapp2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.example.maddbtestapp2.vaccine.Vaccines
import java.sql.DriverManager.getConnection
import java.util.Calendar
import java.util.Date
import java.util.Random
import java.util.UUID
import com.example.maddbtestapp2.databaseConfig.DbConnect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddNewVaccActivity : BaseActivity() {

    private lateinit var inputVaccName: EditText
    private lateinit var dateAdministered: Date
    private lateinit var nextDoseDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_vacc)

        inputVaccName = findViewById(R.id.editVaccName)

        // Get references to the buttons
        val btnAdministeredDate = findViewById<Button>(R.id.btnSetUpDate)
        val btnNextDoseDate = findViewById<Button>(R.id.btnSetUpDate2)
        val homeButton = findViewById<ImageView>(R.id.homeButton2)


        // Set OnClickListener on the administered date button
        btnAdministeredDate.setOnClickListener {
            // Create DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    // This lambda function is called when a date is selected in the DatePickerDialog
                    // Save the selected date to dateAdministered
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth, 0, 0, 0)
                    calendar.set(Calendar.MILLISECOND, 0)
                    dateAdministered = calendar.time
                },
                // The initial date to show in the DatePickerDialog
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // Set OnClickListener on the next dose date button
        btnNextDoseDate.setOnClickListener {
            // Create DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    // This lambda function is called when a date is selected in the DatePickerDialog
                    // Save the selected date to nextDoseDate
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth, 0, 0, 0)
                    nextDoseDate = calendar.time
                },
                // The initial date to show in the DatePickerDialog
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }


        // Get a reference to the "Save" button
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Set an OnClickListener on the "Save" button
        btnSave.setOnClickListener {
            // Get the data from the EditText fields and the selected dates
            val vaccName = inputVaccName.text.toString()

            val vaccine = Vaccines(
                id = null,
                vaccineName = vaccName,
                administeredDate = java.sql.Date(dateAdministered.time),
                nextDoseDate = java.sql.Date(nextDoseDate.time)
            )

            CoroutineScope(Dispatchers.IO).launch {
                val connection = DbConnect.getConnection()
                val vaccinesQueries = VaccinesQueries(connection = connection)
                vaccinesQueries.insertVaccine(vaccine)


                CoroutineScope(Dispatchers.Main).launch {
                    val intent = Intent(this@AddNewVaccActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        homeButton.setOnClickListener {
            goToMainActivity()
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}