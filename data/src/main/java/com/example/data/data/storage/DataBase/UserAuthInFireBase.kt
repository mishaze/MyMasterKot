package com.example.data.data.storage.DataBase

import com.example.data.data.storage.interfaces.UserAuthInFireBase
import com.example.domain.Domain.models.UserInformation
import com.google.firebase.auth.FirebaseAuth


class UserAuthInFireBase(private var auth: FirebaseAuth) :
    UserAuthInFireBase {

    override suspend fun sigInUser(email: String, password: String) {
        val result = auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {

            }.addOnFailureListener { e -> }

    }

    override suspend fun register(email: String, password: String, name: String) {
        val result = auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = UserInformation(email=email, name = name, uid = FirebaseAuth.getInstance().uid)
                UserInformationStorage().saveUserInformation(user)
            }.addOnFailureListener { e -> }


    }

    override fun getFirebaseAuthState() {
        TODO("Not yet implemented")
    }
}