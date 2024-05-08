package com.example.maddbtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.adapters.VaccinationAdapter
import com.example.maddbtestapp2.adapters.VaccineHistoryAdapter

class VaccinationActivity : AppCompatActivity() {

    private lateinit var vaccinationHistoryAdapter: VaccineHistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination)

        val btnSchedule = findViewById<Button>(R.id.btnSchdApp)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        btnSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleAppActivity::class.java)
            startActivity(intent)
        }

        vaccinationHistoryAdapter = VaccineHistoryAdapter(emptyList()) { vaccinationHistory ->
            val intent = Intent(this, VaccinationActivity::class.java)
            intent.putExtra("vaccName", vaccinationHistory.vaccineName)
            intent.putExtra("doseNumber", vaccinationHistory.doseNumber)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewVaccHist)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vaccinationHistoryAdapter
    }
}