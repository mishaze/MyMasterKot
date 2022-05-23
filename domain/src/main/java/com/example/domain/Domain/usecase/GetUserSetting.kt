package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.example.domain.Domain.models.responses.ResponseSetting
import com.example.domain.Domain.repository.SettingRepository

class GetUserSetting(private val settingRepository: SettingRepository) {
    fun execute(callback: FirebaseCallback<ResponseSetting>) {
        settingRepository.getSetting(callback)
    }
}