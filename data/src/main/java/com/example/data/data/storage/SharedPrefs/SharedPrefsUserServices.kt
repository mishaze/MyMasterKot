package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ServicesStorage
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SharedPrefsUserServices() : ServicesStorage {


    override fun saveUserInformation(servicesList: ArrayList<ServicesModel>) {
        //mDatabase.child("Services").setValue(servicesList)


        servicesList.forEach {
            if (it.uidServices.equals("")) {
                val temp =         FirebaseDatabase.getInstance()
                    .getReference("Master")
                    .child(
                        FirebaseAuth.getInstance()
                            .currentUser?.uid.toString()
                    ).child("Services").push().key.toString()
                it.uidServices = temp
                FirebaseDatabase.getInstance()
                    .getReference("Master")
                    .child(
                        FirebaseAuth.getInstance()
                            .currentUser?.uid.toString()
                    ).child("Services").child(temp).setValue(it)
            } else {
                FirebaseDatabase.getInstance()
                    .getReference("Master")
                    .child(
                        FirebaseAuth.getInstance()
                            .currentUser?.uid.toString()
                    ).child("Services").child(it.uidServices.toString()).setValue(it)
            }
        }
    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseServicesList>) {
        val result = ResponseServicesList()
        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(
                FirebaseAuth.getInstance()
                    .currentUser?.uid.toString()
            ).child("Services").addListenerForSingleValueEvent(object : ValueEventListener {
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