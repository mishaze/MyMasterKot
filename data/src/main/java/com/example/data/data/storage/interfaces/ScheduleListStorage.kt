package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList

interface ScheduleListStorage {

    fun getScheduleList(callback: FirebaseCallback<ResponseScheduleList>)
}