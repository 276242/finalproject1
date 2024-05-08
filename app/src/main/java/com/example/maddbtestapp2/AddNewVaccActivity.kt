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
import java.util.Calendar
import java.util.Date

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

        // Set OnClickListener on the administered date button
        btnAdministeredDate.setOnClickListener {
            // Create DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    // This lambda function is called when a date is selected in the DatePickerDialog
                    // Save the selected date to dateAdministered
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
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
                    calendar.set(year, month, dayOfMonth)
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
            val administeredDate = dateAdministered.time
            val nextDoseDate = nextDoseDate.time

            // Optionally, you can start the next activity here
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("vaccName", vaccName)
            intent.putExtra("administeredDate", administeredDate)
            intent.putExtra("nextDoseDate", nextDoseDate)
            startActivity(intent)
        }
    }
}