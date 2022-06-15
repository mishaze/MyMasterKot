package com.example.domain.Domain.models

data class UserInformation(
    var uid: String? = "",
    var name: String? = "",
    var surname: String? = "",
    var phone_number: String? = "",
    var specialization: String? = "",
    var legal_information: String? = "",
    var email: String? = "",
    var master_info: String? = "",
    var address: String? = "",
    var definition: String? = "0"
)