package com.example.mymaster.presentations.myProfileActivity

import androidx.lifecycle.*
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.usecase.GetUserProfileUseCase
import com.example.domain.Domain.usecase.SetUserProfileUseCase


class MyProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val setUserProfileUseCase: SetUserProfileUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<UserInformation?>()
    val resultLive: LiveData<UserInformation?> = resultLiveMutable

    fun save(user: UserInformation) {
        setUserProfileUseCase.execute(user)
        resultLiveMutable.value = user
    }

    fun load() {
        getUserProfileUseCase.execute(object : FirebaseCallback<ResponseUserInformation> {
            override fun onResponse(response: ResponseUserInformation) {
                resultLiveMutable.value = response.answer
            }

        })

    }
}