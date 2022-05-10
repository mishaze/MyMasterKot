package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.ClientInform

data class ResponseListFriendMaster (
    var answer: ArrayList <ClientInform> = ArrayList(),
    var exception: Exception? = null
)