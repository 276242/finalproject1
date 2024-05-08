package com.example.maddbtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.adapters.VaccinationAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddVaccine: FloatingActionButton
    private lateinit var vaccinationAdapter: VaccinationAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewVacc)
        fabAddVaccine = findViewById(R.id.addNewVaccineFAB)
        val homeButton = findViewById<ImageView>(R.id.homeButton)
        val countdownButton = findViewById<ImageView>(R.id.countdownButton)

        vaccinationAdapter = VaccinationAdapter(emptyList()) { vaccination ->
            val intent = Intent(this, VaccinationActivity::class.java)
            intent.putExtra("vaccName", vaccination.vaccineName)
            intent.putExtra("administeredDate", vaccination.administeredDate.time)
            intent.putExtra("nextDoseDate", vaccination.nextDoseDate.time)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = vaccinationAdapter

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