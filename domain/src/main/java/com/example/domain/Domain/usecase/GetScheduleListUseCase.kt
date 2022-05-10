package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.domain.Domain.repository.ScheduleRepository


class GetScheduleListUseCase(private val scheduleRepository: ScheduleRepository) {

    fun execute(callback: FirebaseCallback<ResponseScheduleList>) {
        scheduleRepository.getSchedule(callback)
    }
}