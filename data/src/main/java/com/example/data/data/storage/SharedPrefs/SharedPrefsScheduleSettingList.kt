package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.ScheduleSettingStorage
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.google.firebase.database.DatabaseReference


class SharedPrefsScheduleSettingList(private val mDatabase: DatabaseReference):ScheduleSettingStorage{

    override fun saveScheduleSettingList(scheduleSettingList: ArrayList<ScheduleSettingModel>) {
        mDatabase.child("schedule").setValue(scheduleSettingList)
    }

    override fun getScheduleSettingList(callback: FirebaseCallback<ResponseScheduleSettingList>) {

        mDatabase.get().addOnCompleteListener { task ->
            val response = ResponseScheduleSettingList()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.answer = result.getValue(ScheduleSettingModel::class.java)!!
                }
            } else {
                response.exception = task.exception
            }
            callback.onResponse(response)
        }



    }
}