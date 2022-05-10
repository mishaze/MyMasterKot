package com.example.domain.Domain.repository

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.ResponseUserInformation

interface UserInformationRepository {

    fun saveUserInformation(user: UserInformation): Boolean

    fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>)
}