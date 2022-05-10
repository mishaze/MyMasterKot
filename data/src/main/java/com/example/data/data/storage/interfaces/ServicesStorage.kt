package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList

interface ServicesStorage {

    fun saveUserInformation(servicesList: ArrayList<ServicesModel> )

    fun getUserInformation(callback: FirebaseCallback<ResponseServicesList>)
}