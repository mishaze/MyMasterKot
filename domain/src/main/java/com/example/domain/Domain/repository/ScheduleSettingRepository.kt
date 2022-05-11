package com.example.domain.Domain.repository

import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList

interface ScheduleSettingRepository {

    fun setScheduleSetting(scheduleSettingList:  ArrayList<ScheduleSettingModel>)

    fun getScheduleSetting(callback: FirebaseCallback<ResponseScheduleSettingList>)

}