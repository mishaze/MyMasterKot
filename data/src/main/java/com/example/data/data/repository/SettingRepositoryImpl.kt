package com.example.data.data.repository

import com.example.data.data.storage.interfaces.SettingStorage
import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseSetting
import com.example.domain.Domain.repository.SettingRepository

class SettingRepositoryImpl(private val settingStorage: SettingStorage):SettingRepository {
    override fun setSetting(setting: SettingModel) {
    settingStorage.setSetting(setting)
    }

    override fun getSetting(callback: FirebaseCallback<ResponseSetting>) {
    settingStorage.getSetting(callback)
    }
}