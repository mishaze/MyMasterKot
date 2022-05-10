package com.example.data.data.repository

import com.example.data.data.storage.models.UserInformStorageModel
import com.example.data.data.storage.interfaces.UserInformationStorage
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.repository.UserInformationRepository


class UserInformationRepositoryImpl(private val userInformationStorage: UserInformationStorage) : UserInformationRepository {

    override fun saveUserInformation(user: UserInformation): Boolean {

        val userInformStorage = UserInformStorageModel(
            name = user.name,
            surname = user.surname,
            phone_number = user.phone_number,
            specialization = user.specialization,
            legal_information = user.legal_information,
            email = user.email,
            master_info = user.master_info
        )

        return userInformationStorage.saveUserInformation(userInformStorage)
    }

    override fun getUserInformation(callback: FirebaseCallback<ResponseUserInformation>) {
        userInformationStorage.getUserInformation(callback)
    }




}