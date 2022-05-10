package com.example.data.data.storage.interfaces

import com.example.data.data.storage.models.UserInformStorageModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseUserInformation


interface UserInformationStorage {

     fun saveUserInformation(user: UserInformStorageModel): Boolean

     fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>)

     //suspend fun getResponseFromRealtimeDatabaseUsingCoroutines(): com.example.data.data.storage.models.Response
}