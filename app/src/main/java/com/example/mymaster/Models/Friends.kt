package com.example.mymaster.Models

import java.util.*

class Friends {
    var first_name: String? = null
    var second_name: String? = null
    var email: String? = null
    var phone: String? = null
    var uid: String? = null

    constructor() {}
    constructor(first_name: String?, second_name: String?, email: String?, phone: String?, uid: String?) {
        this.first_name = first_name
        this.second_name = second_name
        this.email = email
        this.phone = phone
        this.uid = uid
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as Friends
        return first_name == user.first_name &&
                second_name == user.second_name &&
                email == user.email &&
                phone == user.phone &&
                uid == user.uid
    }

    override fun hashCode(): Int {
        return Objects.hash(first_name, second_name, email, phone, uid)
    }
}