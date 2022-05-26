package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ScheduleSettingStorage
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SharedPrefsScheduleSettingList():ScheduleSettingStorage{

    override fun saveScheduleSettingList(scheduleSettingList: ScheduleSettingModel) {
        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(
                FirebaseAuth.getInstance()
                    .currentUser?.uid.toString()
            ).child("Schedule").setValue(scheduleSettingList)
    }

    override fun getScheduleSettingList(callback: FirebaseCallback<ResponseScheduleSettingList>) {

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(
                FirebaseAuth.getInstance()
                    .currentUser?.uid.toString()
            ).child("Schedule").addListenerForSingleValueEvent(object : ValueEventListener {

            val response = ResponseScheduleSettingList()
            override fun onDataChange(snapshot: DataSnapshot) {

                val temp = snapshot.getValue(ScheduleSettingModel::class.java)
                response.answer = temp!!
                callback.onResponse(response)
            }
            override fun onCancelled(error: DatabaseError) {}
        })

    }
}