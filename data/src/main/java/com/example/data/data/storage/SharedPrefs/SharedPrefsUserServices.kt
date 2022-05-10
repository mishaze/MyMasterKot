package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ServicesStorage
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class SharedPrefsUserServices(private val mDatabase: DatabaseReference): ServicesStorage {

    override fun saveUserInformation(servicesList: ArrayList<ServicesModel>) {
        mDatabase.setValue(servicesList)


    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseServicesList>) {

        mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            val result = ResponseServicesList()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    ds.getValue(ServicesModel::class.java)?.let { result.answer.add(it) }

                }
                callback.onResponse(result)
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    }
}