package com.example.data.data.storage.SharedPrefs

import com.example.data.data.storage.interfaces.SettingStorage
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.domain.Domain.models.responses.ResponseSetting
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SharedPrefsSetting : SettingStorage {

    override fun setSetting(setting: SettingModel) {

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("Schedule").child("num").setValue(setting.num)

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("Schedule").child("time").setValue(setting.time)

    }

    override fun getSetting(callback: FirebaseCallback<ResponseSetting>) {
        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("Schedule")
            .get().addOnCompleteListener { task ->
            val response = ResponseSetting()
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    response.answer = result.getValue(SettingModel::class.java)!!
                }
            } else {
                response.exception = task.exception
            }
            callback.onResponse(response)
        }



    }


}