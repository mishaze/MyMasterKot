package com.example.mymaster.Models

import java.util.*

class Services {
    var name: String? = null
    var time: String? = null
    var price: String? = null

    constructor() {}
    constructor(name: String?, price: String?, time: String?) {
        this.name = name
        this.time = time
        this.price = price
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val services = o as Services
        return name == services.name &&
                time == services.time &&
                price == services.price
    }

    override fun hashCode(): Int {
        return Objects.hash(name, time, price)
    }
}