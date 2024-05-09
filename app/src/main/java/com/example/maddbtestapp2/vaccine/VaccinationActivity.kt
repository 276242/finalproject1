package com.example.maddbtestapp2.vaccine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.EditDateActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.ScheduleAppActivity
import com.example.maddbtestapp2.adapters.VaccineHistoryAdapter

class VaccinationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var vaccinationHistoryAdapter: VaccineHistoryAdapter
    private lateinit var vaccNametv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccination)

        val btnSchedule = findViewById<Button>(R.id.btnSchdApp)
        vaccNametv = findViewById(R.id.vaccNametv)
        recyclerView = findViewById(R.id.recyclerViewVaccHist)
        recyclerView.layoutManager = LinearLayoutManager(this)

        vaccNametv.text = intent.getStringExtra("vaccName")

        btnSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleAppActivity::class.java)
            startActivity(intent)
        }

        vaccinationHistoryAdapter = VaccineHistoryAdapter(emptyList()) { vaccine ->
            val intent = Intent(this, EditDateActivity::class.java)
            intent.putExtra("vaccineName", vaccine.vaccineName)
            startActivity(intent)
        }

        recyclerView.adapter = vaccinationHistoryAdapter
    }
}