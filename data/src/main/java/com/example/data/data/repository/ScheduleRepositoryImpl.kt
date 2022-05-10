package com.example.data.data.repository

import com.example.data.data.storage.interfaces.ScheduleListStorage
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.domain.Domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(private val scheduleListStorage: ScheduleListStorage):ScheduleRepository {
    override fun getSchedule(callback: FirebaseCallback<ResponseScheduleList>) {
        scheduleListStorage.getScheduleList(callback)
    }
}