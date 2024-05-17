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

    fun registerUserFS(activity: RegisterActivity, userInfo: UserFB) {
        mFireStore.collection("users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("FireStoreClass", "Error registering user in Firebase: ${e.message}")
            }
    }

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
