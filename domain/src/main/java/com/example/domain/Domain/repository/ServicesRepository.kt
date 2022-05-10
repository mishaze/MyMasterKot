package com.example.domain.Domain.repository

import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList

interface ServicesRepository {

    fun setServicesList(servicesList: ArrayList<ServicesModel>)

    fun getServicesList(callback: FirebaseCallback<ResponseServicesList>)

}