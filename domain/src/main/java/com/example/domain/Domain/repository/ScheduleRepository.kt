package com.example.domain.Domain.repository

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList

interface ScheduleRepository{

    fun getSchedule(callback: FirebaseCallback<ResponseScheduleList>)

}