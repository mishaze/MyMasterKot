package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.UserInformation
import com.example.domain.Domain.repository.UserInformationRepository

class SetUserProfileUseCase(private val userInformationRepository: UserInformationRepository) {

    fun execute(user: UserInformation): Boolean {

        return userInformationRepository.saveUserInformation(user)

    }

}