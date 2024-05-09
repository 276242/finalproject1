package com.example.maddbtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.adapters.VaccinationAdapter
import com.example.maddbtestapp2.vaccine.VaccinationActivity
import com.example.maddbtestapp2.vaccine.Vaccines
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddVaccine: FloatingActionButton
    private lateinit var vaccinationAdapter: VaccinationAdapter
    private val itemList = mutableListOf<Vaccines>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewVacc)
        fabAddVaccine = findViewById(R.id.addNewVaccineFAB)
        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val countdownButton = findViewById<ImageView>(R.id.countdownButton)


        vaccinationAdapter = VaccinationAdapter(itemList) { vaccination ->
            val intent = Intent(this, VaccinationActivity::class.java)
            intent.putExtra("id", vaccination.id!!.toInt())
            intent.putExtra("vaccName", vaccination.vaccineName)
            intent.putExtra("administeredDate", vaccination.administeredDate)
            intent.putExtra("nextDoseDate", vaccination.nextDoseDate)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vaccinationAdapter

        val id = intent.getIntExtra("id", 0)
        val vaccineName = intent.getStringExtra("vaccName")

        val administeredDate = java.sql.Date(intent.getLongExtra("administeredDate", 0))
        val nextDoseDate = java.sql.Date(intent.getLongExtra("nextDoseDate", 0))

        if (vaccineName != null && administeredDate != null && nextDoseDate != null) {
            val newVaccine = Vaccines(id, vaccineName, administeredDate, nextDoseDate)
            itemList.add(newVaccine)
            vaccinationAdapter.notifyDataSetChanged()
        }

        fabAddVaccine.setOnClickListener {
            val intent = Intent(this, AddNewVaccActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            goToMainActivity()
        }

//        countdownButton.setOnClickListener {
//            goToSpanOfOneBoxActivity()
//        }

    }


    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

//    private fun goToSpanOfOneBoxActivity() {
//        val intent = Intent(this, SpanOfOneBoxActivity::class.java)
//        startActivity(intent)
//        finish()
//    }


//    private fun fetchData() {
//        // Retrieve the Intent that started this activity
//        val intent = getIntent()
//
//    // Retrieve the data from the Intent
//        val vaccName = intent.getStringExtra("vaccName")
//        val administeredDate = Date(intent.getLongExtra("administeredDate", 0))
//        val nextDoseDate = Date(intent.getLongExtra("nextDoseDate", 0))
//    }
}
//