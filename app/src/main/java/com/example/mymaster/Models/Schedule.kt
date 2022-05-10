package com.example.mymaster.Models

import java.util.*

class Schedule {
    var time_start: Int
    var time_finish = 1

    var isEnable = false

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val schedule = o as Schedule
        return time_start == schedule.time_start && time_finish == schedule.time_finish && isEnable == schedule.isEnable
    }

    override fun hashCode(): Int {
        return Objects.hash(time_start, time_finish, isEnable)
    }

    constructor(time_start: Int) {
        this.time_start = time_start
    }
    constructor(start: Int, finish: Int, isEnable: Boolean) {
        time_start = start
        time_finish = finish
        var enable = isEnable
    }
    constructor() {
        time_start = 1
        time_finish = 1
        var enable = false
    }

}