package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.SettingModel

data class ResponseSetting (
        var answer: SettingModel? = SettingModel(),
        var exception: Exception? = null
)
