package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ScheduleListStorage
import com.example.domain.Domain.models.ClientInform
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class SharedPrefsUserScheduleList() : ScheduleListStorage {
    override fun getScheduleList(callback: FirebaseCallback<ResponseScheduleList>) {

        val mrDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("MasterRecords")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val cDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Client")
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser!!.uid).child("Services")

        mrDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            val response = ResponseScheduleList()

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val temp = ds.getValue(RecordingSessionModel::class.java)

                    cDatabase.child(temp?.uid.toString()).child("name").get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val result = task.result
                            result?.let {
                                val cl = result.getValue(String::class.java)
                                temp?.uid = cl

                                mDatabase.child(temp?.uids.toString()).get().addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val result = task.result
                                        result?.let {
                                            val sr= result.getValue(ServicesModel::class.java)
                                            temp?.uids = sr?.name
                                            response.answer.add(temp!!)

                                        }
                                    } else {
                                        response.exception = task.exception
                                    }

                                    callback.onResponse(response)

                                }
                            }
                        } else {
                            response.exception = task.exception
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}