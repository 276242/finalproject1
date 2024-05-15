package com.example.maddbtestapp2

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Activity for editing a date.
 * Allows the user to select a date using DatePickerDialog and returns the selected date to the calling activity.
 */
class EditDateActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var selectedDateButton: Button
    private lateinit var confirmButton: Button
    private var selectedDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_edit_date)


        selectedDateButton = findViewById(R.id.selDateButton)
        confirmButton = findViewById(R.id.confirmButton)


        selectedDateButton.setOnClickListener {
            showDatePickerDialog()
        }


        confirmButton.setOnClickListener {
            if (selectedDate != null) {

                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate!!)
                Toast.makeText(this, "Selected Date: $formattedDate", Toast.LENGTH_SHORT).show()


                val resultIntent = Intent()
                resultIntent.putExtra("selectedDate", selectedDate!!.time)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Shows DatePickerDialog to select a date.
     */
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Show DatePickerDialog
        DatePickerDialog(this, this, year, month, day).show()
    }

    /**
     * Callback method triggered when a date is set in DatePickerDialog.
     * Updates selectedDate and the text of the selectedDateButton with the chosen date.
     */
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        selectedDate = calendar.time


        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.format(selectedDate!!)
        selectedDateButton.text = formattedDate
    }
}
//