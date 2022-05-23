package com.example.domain.Domain.repository

import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.example.domain.Domain.models.responses.ResponseSetting

interface SettingRepository {
    fun setSetting(setting: SettingModel)

    fun getSetting(callback: FirebaseCallback<ResponseSetting>)

}