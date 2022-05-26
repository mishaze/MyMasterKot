package com.example.mymaster.presentations.Settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseSetting
import com.example.domain.Domain.usecase.GetUserSetting
import com.example.domain.Domain.usecase.SetUserSetting

class SettingViewModel(
    private val getUserSettingUseCase: GetUserSetting,
    private val setUserSettingUseCase: SetUserSetting

):ViewModel() {

    private var resultLiveMutable = MutableLiveData<SettingModel?>()
    val resultLive: LiveData<SettingModel?> = resultLiveMutable

    fun save(setting: SettingModel) {
        setUserSettingUseCase.execute(setting)
        resultLiveMutable.value = setting
    }

    fun load() {
        getUserSettingUseCase.execute(object : FirebaseCallback<ResponseSetting> {
            override fun onResponse(response: ResponseSetting) {
                resultLiveMutable.value = response.answer
            }

        })

    }

}