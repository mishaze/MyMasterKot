package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.UserInformation

data class ResponseUserInformation(
    var answer: UserInformation? = null,
    var exception: Exception? = null
)