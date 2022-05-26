package com.example.data.data.repository

import com.example.data.data.storage.models.UserInformStorageModel
import com.example.data.data.storage.interfaces.UserInformationStorage
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.repository.UserInformationRepository


class UserInformationRepositoryImpl(private val userInformationStorage: UserInformationStorage) : UserInformationRepository {

    override fun saveUserInformation(user: UserInformation): Boolean {


        return userInformationStorage.saveUserInformation(user)
    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>) {
        userInformationStorage.getUserInformation(callback)
    }




}