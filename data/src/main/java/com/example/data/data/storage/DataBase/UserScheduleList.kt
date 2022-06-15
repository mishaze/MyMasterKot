package com.example.data.data.storage.DataBase

import com.example.data.data.storage.interfaces.ScheduleListStorage
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class UserScheduleList() : ScheduleListStorage {
    override fun getScheduleList(callback: FirebaseCallback<ResponseScheduleList>) {

        val mrDatabase: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("MasterRecords")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val cDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Client")
        val mDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser!!.uid).child("Services")

        mrDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            val response = ResponseScheduleList()

            override fun onDataChange(snapshot: DataSnapshot) {
                val i = snapshot.childrenCount.toInt()
                for (ds in snapshot.children) {

                    val temp = ds.getValue(RecordingSessionModel::class.java)
                    temp?.uidR = ds.key

                    cDatabase.child(temp?.uid.toString()).child("name").get()

                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val result = task.result
                                result?.let {
                                    val cl = result.getValue(String::class.java)
                                    temp?.name = cl

                                    mDatabase.child(temp?.uids.toString()).get()
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                val result = task.result
                                                result?.let {
                                                    val sr =
                                                        result.getValue(ServicesModel::class.java)
                                                    temp?.uids = sr?.name
                                                    temp?.price = sr?.price

                                                    response.answer.add(temp!!)

                                                }
                                            } else {
                                                response.exception = task.exception
                                            }
                                            if (i == response.answer.size)
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

    override fun setCancelRecord(uidC: String, uidR: String){

        FirebaseDatabase.getInstance()
            .getReference("ClientRecords")
            .child(uidC)
            .child(uidR)
            .child("status")
            .setValue(false)

        FirebaseDatabase.getInstance()
            .getReference("MasterRecords")
            .child(
                FirebaseAuth.getInstance()
                .currentUser?.uid.toString())
            .child(uidR)
            .child("status")
            .setValue(false)

   }
}