package com.example.mymaster.presentations.Stat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.SettingModel
import com.example.domain.Domain.models.StatModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseSetting
import com.example.domain.Domain.models.responses.ResponseUserStat
import com.example.domain.Domain.usecase.GetUserStat

class StatViewModel(private val getUserStat: GetUserStat): ViewModel() {

    private var resultLiveMutable = MutableLiveData<StatModel?>()
    val resultLive: LiveData<StatModel?> = resultLiveMutable


    fun load() {
        getUserStat.execute(object : FirebaseCallback<ResponseUserStat> {
            override fun onResponse(response: ResponseUserStat) {
                resultLiveMutable.value = response.answer
            }
        })
    }

}