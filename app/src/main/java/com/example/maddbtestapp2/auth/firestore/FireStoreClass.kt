/**
 * FireStoreClass is a class that provides functionality to interact with Firebase Firestore.
 *
 * This class provides methods to register a user and fetch users from Firebase Firestore.
 *
 * @property mFireStore The instance of FirebaseFirestore.
 */
package com.example.maddbtestapp2.firestore

import android.util.Log
import com.example.maddbtestapp2.auth.RegisterActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    init {
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        mFireStore.firestoreSettings = settings
    }


    /**
     * Registers a user in Firebase Firestore.
     *
     * @param activity The activity where this method is called from.
     * @param userInfo The user information to register.
     */
    fun registerUserFS(activity: RegisterActivity, userInfo: UserFB) {
        mFireStore.collection("users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
                Log.e("FireStoreClass", "Error registering user in Firebase: ${e.message}")
            }
    }

    /**
     * Fetches users from Firebase Firestore.
     *
     * @return A list of users if successful, an empty list otherwise.
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
