package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.ServicesModel

data class ResponseServicesList (
    var answer: ArrayList <ServicesModel> = ArrayList(),
    var exception: Exception? = null
)