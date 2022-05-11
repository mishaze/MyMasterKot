package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.example.domain.Domain.repository.ScheduleSettingRepository

class GetUserScheduleSettingUseCase(private val scheduleSettingRepository: ScheduleSettingRepository) {

    fun execute(callback: FirebaseCallback<ResponseScheduleSettingList>)
    {
        scheduleSettingRepository.getScheduleSetting(callback)
    }
}