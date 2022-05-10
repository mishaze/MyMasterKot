package com.example.mymaster.Models

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*
@IgnoreExtraProperties
class User(
    var first_name: String? = "",
    var second_name: String? = "",
    var email: String? = "",
    var phone: String? = "",
    var address: String? = "",
    var info: String? = "",
    var uid: String? = "",
    var friends: ArrayList<String?> = ArrayList<String?>(),
    var list_services: ArrayList<String?> = ArrayList<String?>(),
    var schedule: ArrayList<Schedule?> = ArrayList<Schedule?>()
)
{

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (first_name != other.first_name) return false
        if (second_name != other.second_name) return false
        if (email != other.email) return false
        if (phone != other.phone) return false
        if (address != other.address) return false
        if (info != other.info) return false
        if (uid != other.uid) return false
        if (friends != other.friends) return false
        if (list_services != other.list_services) return false
        if (schedule != other.schedule) return false

        return true
    }

    override fun hashCode(): Int {
        var result = first_name?.hashCode() ?: 0
        result = 31 * result + (second_name?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (info?.hashCode() ?: 0)
        result = 31 * result + (uid?.hashCode() ?: 0)
        result = 31 * result + friends.hashCode()
        result = 31 * result + list_services.hashCode()
        result = 31 * result + schedule.hashCode()
        return result
    }
}