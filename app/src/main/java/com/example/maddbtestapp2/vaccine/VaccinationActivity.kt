package com.example.maddbtestapp2.vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.history.HistoryQueries
import com.example.maddbtestapp2.MainActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.ScheduleAppActivity
import com.example.maddbtestapp2.adapters.VaccineHistoryAdapter
import com.example.maddbtestapp2.databaseConfig.DbConnect
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.LocalDate
import java.util.Calendar

class VaccinationActivity : AppCompatActivity(), VaccineHistoryAdapter.OnDeleteClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vaccinationHistoryAdapter: VaccineHistoryAdapter
    private lateinit var vaccNametv: TextView
    private val vaccinationItems = mutableListOf<VaccineHistoryItem>()
    private var vaccineId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination)

        val homeButton = findViewById<ImageView>(R.id.homeButton4)
        val scheduleButton = findViewById<ImageView>(R.id.scheduleButton4)

        val editNameButton = findViewById<Button>(R.id.btnEditName)
        val fabAddAdmDate = findViewById<FloatingActionButton>(R.id.addNewAdmDateFAB)


        vaccNametv = findViewById(R.id.vaccNametv)
        recyclerView = findViewById(R.id.recyclerViewVaccHist)
        recyclerView.layoutManager = LinearLayoutManager(this)

        vaccNametv.text = intent.getStringExtra("vaccName")


        vaccinationHistoryAdapter = VaccineHistoryAdapter(vaccinationItems, this, vaccNametv.text.toString())

        CoroutineScope(Dispatchers.IO).launch {
            val connection = DbConnect.getConnection()
            val vaccinesQueries = VaccinesQueries(connection = connection)
            val historyQueries = HistoryQueries(connection = connection)

            val vaccineName = vaccNametv.text.toString()
            vaccineId = vaccinesQueries.getVaccineIdByVaccineName(vaccineName)
            val histories = historyQueries.getHistoryByVaccineId(vaccineId)
            if (histories != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    for (history in histories) {
                        vaccinationItems.add(VaccineHistoryItem(history.id, history.vaccineId.toString(), history.administeredDate))
                    }
                    vaccinationHistoryAdapter.notifyDataSetChanged()
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                vaccinationHistoryAdapter.notifyDataSetChanged()
            }
        }

        recyclerView.adapter = vaccinationHistoryAdapter

        homeButton.setOnClickListener {
            goToMainActivity()
        }

        scheduleButton.setOnClickListener {
            goToScheduleActivity()
        }

        editNameButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_edit_name, null)
            val editTextNewName = dialogLayout.findViewById<EditText>(R.id.editTextNewName)

            editTextNewName.setText(vaccNametv.text.toString())

            builder.setTitle("Edit Vaccine Name")
            builder.setView(dialogLayout)
            builder.setPositiveButton("Update") { dialogInterface, i ->
                val newVaccineName = editTextNewName.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val connection = DbConnect.getConnection()
                    val vaccinesQueries = VaccinesQueries(connection = connection)
                    val vaccineName = vaccNametv.text.toString()
                    vaccineId = vaccinesQueries.getVaccineIdByVaccineName(vaccineName)
                    vaccinesQueries.updateVaccineName(vaccineId, newVaccineName)
                    connection.close()
                    CoroutineScope(Dispatchers.Main).launch {
                        vaccNametv.text = newVaccineName
                    }
                }
            }
            builder.setNegativeButton("Cancel") { dialogInterface, i ->
                // User cancelled the dialog, no action needed
            }

            builder.show()
        }

        fabAddAdmDate.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_choose_date, null, false)  // Set attachToRoot to false
            val datePicker = dialogLayout.findViewById<DatePicker>(R.id.datePicker)

            builder.setTitle("Add New Administration Date")
            builder.setView(dialogLayout)
            builder.setNegativeButton("Cancel") { dialogInterface, i ->
                // User cancelled the dialog, no action needed
            }


            builder.setPositiveButton("Save") { dialogInterface, i ->
                val day = datePicker.dayOfMonth
                val month = datePicker.month
                val year = datePicker.year

                val calendar = Calendar.getInstance()
                calendar.set(year, month, day)
                val selectedDate = Date(calendar.timeInMillis)

                CoroutineScope(Dispatchers.IO).launch {
                    val connection = DbConnect.getConnection()
                    val historyQueries = HistoryQueries(connection = connection)
                    val vaccinesQueries = VaccinesQueries(connection = connection)

                    vaccineId = vaccinesQueries.getVaccineIdByVaccineName(vaccNametv.text.toString())

                    val newRecordId = historyQueries.addNewRecord(vaccineId, selectedDate)
                    connection.close()
                    CoroutineScope(Dispatchers.Main).launch {
                        val newItem = VaccineHistoryItem(newRecordId, vaccineId.toString(), selectedDate)
                        vaccinationHistoryAdapter.addNewItem(newItem)
                    }
                }
            }

            builder.show()
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

    override fun onDeleteClicked(item: VaccineHistoryItem) {
        CoroutineScope(Dispatchers.IO).launch {
            val connection = DbConnect.getConnection()
            val historyQueries = HistoryQueries(connection=connection)
            item.historyId?.let { historyQueries.deleteHistory(it) }
            vaccinationItems.remove(item)
            CoroutineScope(Dispatchers.Main).launch {
                vaccinationHistoryAdapter.notifyDataSetChanged()
            }
        }
    }

//    override fun onDeleteClicked(item: VaccineHistoryItem) {
//       val connection = DbConnect.getConnection()
//       val historyQueries = HistoryQueries(connection)
//        CoroutineScope(Dispatchers.IO).launch {
//            item.historyId?.let { historyQueries.deleteHistory(it) }
//            vaccinationItems.remove(item)
//            CoroutineScope(Dispatchers.Main).launch {
//                vaccinationHistoryAdapter.notifyDataSetChanged()
//            }
//        }
//    }
}
