package com.example.mymaster.Models

import java.util.*

class ScheduleItem {
    var name: String
    var services: String? = null
    var time_start: String? = null
    var time_end: String? = null

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, services: String?, time_start: String?, time_end: String?) {
        this.name = name
        this.services = services
        this.time_start = time_start
        this.time_end = time_end
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ScheduleItem
        return name == that.name &&
                services == that.services &&
                time_start == that.time_start &&
                time_end == that.time_end
    }

    override fun hashCode(): Int {
        return Objects.hash(name, services, time_start, time_end)
    }
}