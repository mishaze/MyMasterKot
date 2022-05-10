package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseUserInformation
import com.example.domain.Domain.repository.UserInformationRepository

class GetUserProfileUseCase(private val userInformationRepository: UserInformationRepository) {

     fun execute(callback: FirebaseCallback<ResponseUserInformation>) {
         userInformationRepository.getUserInformation(callback)
    }
}