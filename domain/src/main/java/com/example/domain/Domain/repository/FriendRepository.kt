package com.example.domain.Domain.repository

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseListFriendMaster

interface FriendRepository {

    fun setInFriend(email: String)

    fun getInFriend(callback: FirebaseCallback<ResponseListFriendMaster>)

}