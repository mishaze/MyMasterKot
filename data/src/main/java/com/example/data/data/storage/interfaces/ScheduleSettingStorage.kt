package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.example.domain.Domain.models.responses.ResponseServicesList

interface ScheduleSettingStorage {

    fun saveScheduleSettingList(scheduleSettingList: ArrayList<ScheduleSettingModel> )

    fun getScheduleSettingList(callback: FirebaseCallback<ResponseScheduleSettingList>)

}