package com.example.data.data.storage.models

import java.util.ArrayList

class ClientInformStorageModel {
    var email:String? = ""
    var name:String? = ""
    var phone_number:String? = ""
    var surname:String? = ""
    var friends: ArrayList<String?> = ArrayList<String?>()
    var uid: String? = ""
}