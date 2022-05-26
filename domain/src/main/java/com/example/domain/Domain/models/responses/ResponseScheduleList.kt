package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.RecordingSessionModel

data class ResponseScheduleList(
    var answer: ArrayList<RecordingSessionModel> = ArrayList<RecordingSessionModel>(),
    var exception: Exception? = null
)