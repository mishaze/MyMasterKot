package com.example.mymaster.presentations.ServicesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.ServicesModel
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseServicesList
import com.example.domain.Domain.usecase.GetUserServicesListUseCase
import com.example.domain.Domain.usecase.SetUserServicesListUseCase

class ServiceListViewModel(private val getUserServicesListUseCase: GetUserServicesListUseCase,
                           private val setUserServicesListUseCase: SetUserServicesListUseCase
):ViewModel() {

    private var resultLiveMutable = MutableLiveData<ArrayList<ServicesModel>>()
    val resultLive: LiveData<ArrayList<ServicesModel>> = resultLiveMutable

    fun save(servicesList: ArrayList<ServicesModel>) {
        setUserServicesListUseCase.execute(servicesList)
        resultLiveMutable.value = servicesList
    }

    fun load() {
        getUserServicesListUseCase.execute(object : FirebaseCallback<ResponseServicesList> {
            override fun onResponse(response: ResponseServicesList) {
                resultLiveMutable.value = response.answer
            }
        })
    }



}