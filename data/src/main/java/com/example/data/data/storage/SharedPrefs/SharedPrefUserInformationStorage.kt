package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.UserInformationStorage
import com.example.data.data.storage.models.UserInformStorageModel
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.google.firebase.database.*

class SharedPrefUserInformationStorage(private val mDatabase: DatabaseReference) :
    UserInformationStorage {
    override fun saveUserInformation(user: UserInformStorageModel): Boolean {
        // if (firstName.text.toString().length > 1) {
        mDatabase.setValue(user)
        //mDatabase.child("name").setValue(user.name)
        //mDatabase.child("surname").setValue(user.surname)
        //mDatabase.child("phone_number").setValue(user.phone_number)
        //mDatabase.child("legal_information").setValue(user.legal_information)
        //mDatabase.child("master_info").setValue(user.master_info)
        //mDatabase.child("email").setValue(user.email)
        //mDatabase.child("specialization").setValue(user.specialization)
        return true
    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>){

        mDatabase.get().addOnCompleteListener { task ->
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

