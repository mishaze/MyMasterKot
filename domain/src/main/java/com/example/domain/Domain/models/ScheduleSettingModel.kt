package com.example.domain.Domain.models


data class ScheduleSettingModel(
    var time: String? = "",
    var num: Int? = 0,
    var dayOfWeek: ArrayList<DayWeek> = ArrayList<DayWeek>()
)
