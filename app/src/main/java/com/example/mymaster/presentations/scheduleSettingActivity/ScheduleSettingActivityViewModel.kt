package com.example.mymaster.presentations.scheduleSettingActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.ScheduleSettingModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleSettingList
import com.example.domain.Domain.usecase.GetUserScheduleSettingUseCase
import com.example.domain.Domain.usecase.SetUserScheduleSettingUseCase


class ScheduleSettingActivityViewModel(
    private val getUserScheduleSettingUseCase: GetUserScheduleSettingUseCase,
    private val setUserScheduleSettingUseCase: SetUserScheduleSettingUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<ScheduleSettingModel>()
    val resultLive: LiveData<ScheduleSettingModel> = resultLiveMutable

    fun save(scheduleSetting: ArrayList<ScheduleSettingModel>) {
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