package com.example.mymaster.presentations.ScheduleSetting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.example.domain.Domain.usecase.GetUserScheduleSettingUseCase
import com.example.domain.Domain.usecase.SetUserScheduleSettingUseCase


class ScheduleSettingViewModel(
    private val getUserScheduleSettingUseCase: GetUserScheduleSettingUseCase,
    private val setUserScheduleSettingUseCase: SetUserScheduleSettingUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<ScheduleSettingModel>()
    val resultLive: LiveData<ScheduleSettingModel> = resultLiveMutable

    private val _index = MutableLiveData<Int>()

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun save(scheduleSetting: ScheduleSettingModel) {
        setUserScheduleSettingUseCase.execute(scheduleSetting)
        //resultLiveMutable.value = scheduleSetting
    }

    fun load() {
        getUserScheduleSettingUseCase.execute(object : FirebaseCallback<ResponseScheduleSettingList> {
            override fun onResponse(response: ResponseScheduleSettingList) {
                resultLiveMutable.value = response.answer
            }
        })
    }



}