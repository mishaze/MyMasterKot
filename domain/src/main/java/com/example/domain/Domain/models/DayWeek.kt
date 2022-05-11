package com.example.domain.Domain.models

data class DayWeek(
    val monday: Day? = Day(),
    val tuesday: Day? = Day(),
    val wednesday: Day? = Day(),
    val thursday: Day? =Day(),
    val friday: Day? = Day(),
    val saturday: Day? = Day(),
    val sunday: Day? = Day()
)