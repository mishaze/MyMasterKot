package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.domain.Domain.models.responses.ResponseServicesList

interface ScheduleListStorage {

    fun getScheduleList(callback: FirebaseCallback<ResponseScheduleList>)
}