package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.repository.SettingRepository

class SetUserSetting(private val settingRepository: SettingRepository) {
    fun execute(setting: SettingModel){
        settingRepository.setSetting(setting)
    }
}