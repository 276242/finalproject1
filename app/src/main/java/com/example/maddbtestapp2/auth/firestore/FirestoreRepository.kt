import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreRepository {
    private val db: FirebaseFirestore = Firebase.firestore

    fun addUser(name: String, email: String) {
        val user: MutableMap<String, Any> = HashMap()
        user["name"] = name
        user["email"] = email

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("FirestoreRepository", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("FirestoreRepository", "Error adding document", e)
            }
    }

    fun fetchUsers() {
        db.collection("users")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("FirestoreRepository", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("FirestoreRepository", "Error getting documents: ", exception)
            }
    }
}