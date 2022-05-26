package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseUserInformation


interface UserInformationStorage {

     fun saveUserInformation(user: UserInformation): Boolean

     fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>)

     //suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): com.example.data.data.storage.models.Response
}