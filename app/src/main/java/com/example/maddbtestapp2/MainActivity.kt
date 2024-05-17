/**
 * MainActivity is the entry point of the application.
 *
 * This activity is responsible for displaying the list of vaccines a user has taken and provides options to add new vaccines.
 * It fetches the list of vaccines from Firestore and displays them in a RecyclerView.
 * Each item in the RecyclerView represents a vaccine and clicking on it navigates the user to the VaccinationActivity where the details of the vaccine can be viewed.
 * The FloatingActionButton on this screen is used to add a new vaccine, clicking on it navigates the user to the AddNewVaccActivity.
 * The homeButton navigates the user back to the MainActivity and the scheduleButton navigates the user to the ScheduleAppActivity.
 *
 * @property recyclerView The RecyclerView used to display the list of vaccines.
 * @property fabAddVaccine The FloatingActionButton used to add a new vaccine.
 * @property vaccinationAdapter The adapter used to populate the RecyclerView.
 * @property itemList The list of vaccines fetched from Firestore.
 * @property connection The connection to the database.
 * @property vaccinesQueries The queries used to interact with the database.
 * @property firestoreRepository The repository used to interact with Firestore.
 */
package com.example.maddbtestapp2

import FirestoreRepository
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        firestoreRepository.fetchUsers()

        recyclerView = findViewById(R.id.recyclerViewVacc)
        fabAddVaccine = findViewById(R.id.addNewAdmDateFAB)
        val homeButton = findViewById<ImageView>(R.id.homeButton2)
        val scheduleButton = findViewById<ImageView>(R.id.scheduleButton3)

        vaccinationAdapter = VaccinationAdapter(itemList) { vaccination ->
            val intent = Intent(this, VaccinationActivity::class.java)
            intent.putExtra("id", vaccination.id!!.toInt())
            intent.putExtra("vaccName", vaccination.vaccineName)
            intent.putExtra("administeredDate", vaccination.administeredDate)
            intent.putExtra("nextDoseDate", vaccination.nextDoseDate)
            startActivity(intent)
        }

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
}