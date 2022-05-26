package com.example.data.data.storage.interfaces

import com.example.domain.Domain.models.responses.FirebaseCallback

interface FriendStorage {

    fun addInMasterFriend(email :String)

    fun getListMasterFriend(callback: FirebaseCallback<com.example.domain.Domain.models.responses.ResponseListFriendMaster>)
}