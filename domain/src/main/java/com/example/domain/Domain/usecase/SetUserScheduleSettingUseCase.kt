package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.repository.ScheduleSettingRepository


class SetUserScheduleSettingUseCase(private val scheduleSettingRepository: ScheduleSettingRepository) {
    fun execute(user: ScheduleSettingModel) {
        scheduleSettingRepository.setScheduleSetting(user)
    }
}