package com.example.mymaster.presentations.scheduleActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.RecordingSessionModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.domain.Domain.usecase.GetScheduleListUseCase

class ScheduleActivityViewModel (
    private val getUserScheduleListUSeCase: GetScheduleListUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData <ArrayList<RecordingSessionModel>>()
    val resultLive: MutableLiveData<ArrayList<RecordingSessionModel>> = resultLiveMutable

    fun getFriendList() {
        getUserScheduleListUSeCase.execute(object : FirebaseCallback<ResponseScheduleList> {
            override fun onResponse(response: ResponseScheduleList) {
                resultLiveMutable.value = response.answer
            }
        })
    }

}