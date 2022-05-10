package com.example.data.data.repository

import com.example.data.data.storage.interfaces.FriendStorage
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseListFriendMaster
import com.example.domain.Domain.repository.FriendRepository

class MasterFriendRepositoryImpl(private val addInFriendStorage: FriendStorage) :
    FriendRepository {

    override fun setInFriend(email: String) {
        addInFriendStorage.addInMasterFriend(email)
    }

    override fun getInFriend(callback: FirebaseCallback<ResponseListFriendMaster>) {
        addInFriendStorage.getListMasterFriend(callback)
    }
}