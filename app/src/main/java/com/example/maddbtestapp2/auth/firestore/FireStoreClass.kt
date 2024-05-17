package com.example.maddbtestapp2.firestore

import com.example.maddbtestapp2.auth.RegisterActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUserFS(activity: RegisterActivity, userInfo: UserFB) {
        mFireStore.collection("users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    suspend fun fetchUsersFromFirebase(): List<UserFB> {
        return try {
            val snapshot = mFireStore.collection("users").get().await()
            snapshot.toObjects(UserFB::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
