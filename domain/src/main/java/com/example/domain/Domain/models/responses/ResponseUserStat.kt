package com.example.domain.Domain.models.responses

import com.example.domain.Domain.models.StatModel

data class ResponseUserStat(
    var answer: StatModel? = StatModel(),
    var exception: Exception? = null
)