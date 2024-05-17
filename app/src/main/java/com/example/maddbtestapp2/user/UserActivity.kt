package com.example.maddbtestapp2.user

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.maddbtestapp2.firestore.FireStoreClass
import com.example.maddbtestapp2.firestore.UserFB
import com.example.maddbtestapp2.databaseConfig.DbConnect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private val firestoreClass = FireStoreClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchUsersFromFirebaseAndSaveToDatabase()
    }

    fun fetchUsersFromFirebaseAndSaveToDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val usersFB = firestoreClass.fetchUsersFromFirebase()
                if (usersFB.isNotEmpty()) {
                    val users = usersFB.map { userFB ->
                        User(
                            user_id = userFB.id.toInt(),
                            name = userFB.name,
                            email = userFB.email
                        )
                    }
                    saveUsersToLocalDatabase(users)
                } else {
                    Log.d("UserActivity", "No users found in Firebase.")
                }
            } catch (e: Exception) {
                Log.e("UserActivity", "Error fetching users from Firebase: ${e.message}")
            }
        }
    }

    private suspend fun saveUsersToLocalDatabase(users: List<User>) {
        val connection = DbConnect.getConnection()
        val userQueries = UserQueries(connection)
        users.forEach { user ->
            userQueries.insertUser(user)
        }
        connection.close()
        Log.d("UserActivity", "Users saved to local database successfully.")
    }
}