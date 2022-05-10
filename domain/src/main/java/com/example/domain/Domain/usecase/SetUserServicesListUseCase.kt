package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.repository.ServicesRepository

class SetUserServicesListUseCase(private val servicesRepository: ServicesRepository) {

    fun execute(servicesList: ArrayList<ServicesModel>){
        servicesRepository.setServicesList(servicesList)
    }
}