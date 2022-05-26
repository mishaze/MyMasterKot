package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.example.domain.Domain.repository.ServicesRepository

class GetUserServicesListUseCase(private val servicesRepository: ServicesRepository) {

        fun execute(callback: FirebaseCallback<ResponseServicesList>) {
            servicesRepository.getServicesList(callback)
        }
}