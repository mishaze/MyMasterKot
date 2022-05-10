package com.example.mymaster.Models

import java.util.*

class Clients {
    var first_name = ""
    var second_name = ""
    var email = ""
    var phone = ""
    var uid = ""

    constructor() {}
    constructor(first_name: String, second_name: String, email: String, phone: String) {
        this.first_name = first_name
        this.second_name = second_name
        this.email = email
        this.phone = phone
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val clients = o as Clients
        return first_name == clients.first_name &&
                second_name == clients.second_name &&
                email == clients.email &&
                phone == clients.phone
    }

    override fun hashCode(): Int {
        return Objects.hash(first_name, second_name, email, phone)
    }
}