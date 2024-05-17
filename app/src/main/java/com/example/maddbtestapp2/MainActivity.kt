package com.example.maddbtestapp2

import FirestoreRepository
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maddbtestapp2.AddNewVaccActivity
import com.example.maddbtestapp2.R
import com.example.maddbtestapp2.ScheduleAppActivity
import com.example.maddbtestapp2.adapters.VaccinationAdapter
import com.example.maddbtestapp2.databaseConfig.DbConnect
import com.example.maddbtestapp2.vaccine.VaccinationActivity
import com.example.maddbtestapp2.vaccine.Vaccines
import com.example.maddbtestapp2.vaccine.VaccinesQueries
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Connection

/**
 * The main activity of the application, displaying a list of vaccinations.
 * Users can add new vaccinations and view details of existing ones.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddVaccine: FloatingActionButton
    private lateinit var vaccinationAdapter: VaccinationAdapter
    private val itemList = mutableListOf<Vaccines>()

    private lateinit var connection: Connection
    private lateinit var vaccinesQueries: VaccinesQueries

    private val firestoreRepository = FirestoreRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Fetch users from Firestore
        firestoreRepository.fetchUsers()

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewVacc)
        fabAddVaccine = findViewById(R.id.addNewAdmDateFAB)
        val homeButton = findViewById<ImageView>(R.id.homeButton2)
        val scheduleButton = findViewById<ImageView>(R.id.scheduleButton3)

        // Initialize adapter
        vaccinationAdapter = VaccinationAdapter(itemList) { vaccination ->
            val intent = Intent(this, VaccinationActivity::class.java)
            intent.putExtra("id", vaccination.id!!.toInt())
            intent.putExtra("vaccName", vaccination.vaccineName)
            intent.putExtra("administeredDate", vaccination.administeredDate)
            intent.putExtra("nextDoseDate", vaccination.nextDoseDate)
            startActivity(intent)
        }

        // Fetch data from database using coroutines
        CoroutineScope(Dispatchers.IO).launch {
            val connection = DbConnect.getConnection()
            val vaccinesQueries = VaccinesQueries(connection = connection)

            val vaccines = vaccinesQueries.getAllVaccines()
            if (vaccines != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    itemList.addAll(vaccines.filterNotNull())
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = vaccinationAdapter
                }
            }
        }

        // Set click listeners
        fabAddVaccine.setOnClickListener {
            val intent = Intent(this, AddNewVaccActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            goToMainActivity()
        }

        scheduleButton.setOnClickListener {
            goToScheduleActivity()
        }
    }

    /**
     * Navigate to the main activity.
     */
    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Navigate to the schedule activity.
     */
    private fun goToScheduleActivity() {
        val intent = Intent(this, ScheduleAppActivity::class.java)
        startActivity(intent)
        finish()
    }
}
