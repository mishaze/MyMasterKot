package com.example.data.data.repository

import com.example.data.data.storage.interfaces.ScheduleSettingStorage
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.example.domain.Domain.repository.ScheduleSettingRepository

class ScheduleSettingRepositoryImpl(private val scheduleSettingStorage: ScheduleSettingStorage) :
    ScheduleSettingRepository {
    override fun setScheduleSetting(scheduleSettingList: ScheduleSettingModel) {
        scheduleSettingStorage.saveScheduleSettingList(scheduleSettingList)
    }

    override fun getScheduleSetting(callback: FirebaseCallback<ResponseScheduleSettingList>) {
        scheduleSettingStorage.getScheduleSettingList(callback)
    }
}