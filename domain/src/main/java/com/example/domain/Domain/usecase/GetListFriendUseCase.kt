package com.example.domain.Domain.usecase

import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseListFriendMaster
import com.example.domain.Domain.repository.FriendRepository

class GetListFriendUseCase(private val addInFriendRepository: FriendRepository) {

    fun execute(callback: FirebaseCallback<ResponseListFriendMaster>)
    {
        addInFriendRepository.getInFriend(callback)
    }
}