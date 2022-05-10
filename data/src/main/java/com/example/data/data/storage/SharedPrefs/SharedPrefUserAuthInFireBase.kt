package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.UserAuthInFireBase
import com.example.data.data.storage.models.UserInformStorageModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class SharedPrefUserAuthInFireBase(private var auth: FirebaseAuth,private val mDatabase: DatabaseReference) :
    UserAuthInFireBase {

    override suspend fun sigInUser(email: String, password: String) {
        val result = auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

            }.addOnFailureListener { e -> }

    }

    override suspend fun register(email: String, password: String, name: String) {
        val result = auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = UserInformStorageModel(email=email, name = name, uid = FirebaseAuth.getInstance().uid)
                SharedPrefUserInformationStorage(mDatabase).saveUserInformation(user)
            }.addOnFailureListener { e -> }


    }

    override fun getFirebaseAuthState() {
        TODO("Not yet implemented")
    }
}