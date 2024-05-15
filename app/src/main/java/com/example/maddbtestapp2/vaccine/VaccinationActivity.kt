package com.example.maddbtestapp2.vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.history.HistoryQueries
import com.example.maddbtestapp2.MainActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.ScheduleAppActivity
import com.example.maddbtestapp2.adapters.VaccineHistoryAdapter
import com.example.maddbtestapp2.databaseConfig.DbConnect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class VaccinationActivity : AppCompatActivity(), VaccineHistoryAdapter.OnDeleteClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vaccinationHistoryAdapter: VaccineHistoryAdapter
    private lateinit var vaccNametv: TextView
    private val vaccinationItems = mutableListOf<VaccineHistoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination)

        val btnSchedule = findViewById<Button>(R.id.btnSchdApp)
        val homeButton = findViewById<ImageView>(R.id.homeButton2)

        vaccNametv = findViewById(R.id.vaccNametv)
        recyclerView = findViewById(R.id.recyclerViewVaccHist)
        recyclerView.layoutManager = LinearLayoutManager(this)

        vaccNametv.text = intent.getStringExtra("vaccName")

        btnSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleAppActivity::class.java)
            intent.putExtra("vaccineName", vaccNametv.text.toString())
            startActivity(intent)
        }

        vaccinationHistoryAdapter = VaccineHistoryAdapter(vaccinationItems, this, vaccNametv.text.toString())

        CoroutineScope(Dispatchers.IO).launch {
            val connection = DbConnect.getConnection()
            val vaccinesQueries = VaccinesQueries(connection = connection)
            val historyQueries = HistoryQueries(connection = connection)

            val vaccineName = vaccNametv.text.toString()
            val vaccineId = vaccinesQueries.getVaccineIdByVaccineName(vaccineName)
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
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDeleteClicked(item: VaccineHistoryItem) {
       val connection = DbConnect.getConnection()
       val historyQueries = HistoryQueries(connection)
        CoroutineScope(Dispatchers.IO).launch {
            item.historyId?.let { historyQueries.deleteHistory(it) }
            vaccinationItems.remove(item)
            CoroutineScope(Dispatchers.Main).launch {
                vaccinationHistoryAdapter.notifyDataSetChanged()
            }
        }
    }
}
