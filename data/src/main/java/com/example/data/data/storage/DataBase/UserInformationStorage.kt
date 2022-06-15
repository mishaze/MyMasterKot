package com.example.data.data.storage.DataBase

import com.example.data.data.storage.interfaces.UserInformationStorage
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class UserInformationStorage() :
    UserInformationStorage {
    override fun saveUserInformation(user: UserInformation): Boolean {
        // if (firstName.text.toString().length > 1) {
        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(
                FirebaseAuth.getInstance()
                    .currentUser?.uid.toString()
            ).child("Information").setValue(user)

        saveSearchList(user)
        /*
        mDatabase.child("name").setValue(user.name)
        mDatabase.child("surname").setValue(user.surname)
        mDatabase.child("phone_number").setValue(user.phone_number)
        mDatabase.child("legal_information").setValue(user.legal_information)
        mDatabase.child("master_info").setValue(user.master_info)
        mDatabase.child("email").setValue(user.email)
        mDatabase.child("specialization").setValue(user.specialization)
        */
        return true
    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>) {

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(
                FirebaseAuth.getInstance()
                    .currentUser?.uid.toString()
            ).child("Information").get().addOnCompleteListener { task ->
                val response = ResponseUserInformation()
                if (task.isSuccessful) {
                    val result = task.result
                    result?.let {
                        response.answer = result.getValue(UserInformation::class.java)
                    }
                } else {
                    response.exception = task.exception
                }
                callback.onResponse(response)
            }
    }

    fun saveSearchList(user: UserInformation) {
        if (!user.phone_number.equals("")) {
            if (!user.phone_number?.get(0).toString().equals("+"))
            user.phone_number="+${user.phone_number}"

                FirebaseDatabase.getInstance()
                    .getReference("Search")
                    .child("identificator")
                    .child(user.phone_number.toString())
                    .setValue(
                        FirebaseAuth.getInstance()
                            .currentUser?.uid.toString()
                    )
        }

        if (!user.specialization.equals("")) {
            FirebaseDatabase.getInstance()
                .getReference("Search")
                .child("specialization")
                .child(
                    FirebaseAuth.getInstance()
                        .currentUser?.uid.toString()
                )
                .setValue(user.specialization?.toLowerCase())
        }
    }
    //getResponseFromRealtimeDatabaseUsingCoroutines()
/*
     fun getResponseFromRealtimeDatabaseUsingCoroutines(): com.example.data.data.storage.models.Response {

        val response = com.example.data.data.storage.models.Response()
        try {
            response.products = mDatabase.get().await().children.map { snapShot ->
                snapShot.getValue(UserInformStorageModel::class.java)!!
            }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return com.example.data.data.storage.models.Response()
    }
*/
}

