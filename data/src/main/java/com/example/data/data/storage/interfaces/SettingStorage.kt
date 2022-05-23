package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseSetting

interface SettingStorage {

    fun setSetting(setting: SettingModel)

    fun getSetting(callback: FirebaseCallback<ResponseSetting>)
}