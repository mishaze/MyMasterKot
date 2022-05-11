package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ServicesStorage
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class SharedPrefsUserServices(private val mDatabase: DatabaseReference) : ServicesStorage {

    private val result = ResponseServicesList()

    override fun saveUserInformation(servicesList: ArrayList<ServicesModel>) {
        //mDatabase.child("Services").setValue(servicesList)


        servicesList.forEach {
            if (it.uidServices.equals("")) {
                val temp = mDatabase.child("Services").push().key.toString()
                it.uidServices = temp
                mDatabase.child("Services").child(temp).setValue(it)
            } else {
                mDatabase.child("Services").child(it.uidServices.toString()).setValue(it)
            }
        }
    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseServicesList>) {

        mDatabase.child("Services").addListenerForSingleValueEvent(object : ValueEventListener {
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