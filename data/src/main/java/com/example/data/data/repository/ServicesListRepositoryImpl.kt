package com.example.data.data.repository

import com.example.data.data.storage.interfaces.ServicesStorage
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.example.domain.Domain.repository.ServicesRepository

class ServicesListRepositoryImpl(private val servicesStorage: ServicesStorage):ServicesRepository {
    override fun setServicesList(servicesList: ArrayList<ServicesModel>) {
        servicesStorage.saveUserInformation(servicesList)
    }

    override fun getServicesList(callback: FirebaseCallback<ResponseServicesList>) {
        servicesStorage.getUserInformation(callback)
    }
}