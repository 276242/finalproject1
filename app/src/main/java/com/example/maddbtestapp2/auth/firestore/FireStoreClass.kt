package com.example.maddbtestapp2.firestore

import android.util.Log
import com.example.maddbtestapp2.auth.RegisterActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

/**
 * FireStoreClass is responsible for handling Firebase Firestore operations for the application.
 *
 * @property mFireStore The instance of FirebaseFirestore used to interact with the database.
 */
class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    init {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        mFireStore.firestoreSettings = settings
    }

    /**
     * Registers a user in the Firestore database.
     *
     * @param activity The activity from which this method is called.
     * @param userInfo The user information to be saved in Firestore.
     */
    fun registerUserFS(activity: RegisterActivity, userInfo: UserFB) {
        mFireStore.collection("users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // User registered successfully, you can handle any additional logic here
            }
            .addOnFailureListener { e ->
                Log.e("FireStoreClass", "Error registering user in Firebase: ${e.message}")
            }
    }

    /**
     * Fetches the list of users from the Firestore database.
     *
     * @return A list of UserFB objects fetched from Firestore.
     */
    suspend fun fetchUsersFromFirebase(): List<UserFB> {
        return try {
            val snapshot = mFireStore.collection("users").get().await()
            snapshot.toObjects(UserFB::class.java)
        } catch (e: Exception) {
            Log.e("FireStoreClass", "Error fetching users from Firebase: ${e.message}")
            emptyList()
        }
    }
}
