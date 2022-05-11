package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.ServicesModel

class ResponseScheduleSettingList (
    var answer: ScheduleSettingModel =ScheduleSettingModel(),
    var exception: Exception? = null

)