package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ScheduleListStorage
import com.example.domain.Domain.models.ClientInform
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.google.firebase.database.*


class SharedPrefsUserScheduleList(private val mDatabase: DatabaseReference) : ScheduleListStorage {
    override fun getScheduleList(callback: FirebaseCallback<ResponseScheduleList>) {

        val rsDatabase: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Recording_Session")
        val cDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference("Clients")

        mDatabase.addListenerForSingleValueEvent(object : ValueEventListener {

            val response = ResponseScheduleList()

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val temp = ds.getValue(RecordingSessionModel::class.java)

                    //проверить путь
                    val cQuery = cDatabase.orderByChild("uid").equalTo(temp?.uidClient)
                    cQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (ds in snapshot.children) {
                                val cl = ds.getValue(ClientInform::class.java)
                                temp?.uidClient = cl?.name + cl?.surname

                                //проверить путь
                                val cQ = cDatabase.orderByChild("uid").equalTo(temp?.uidService)
                                cQ.addListenerForSingleValueEvent(object : ValueEventListener {

                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        for (ds in snapshot.children) {
                                            val sr = ds.getValue(ServicesModel::class.java)
                                            temp?.uidService = sr?.name
                                            response.answer.add(temp!!)
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {}
                                })
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {}
                    })
                    callback.onResponse(response)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}