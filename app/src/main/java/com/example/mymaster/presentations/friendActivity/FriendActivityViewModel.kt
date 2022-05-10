package com.example.mymaster.presentations.friendActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Domain.models.ClientInform
import com.example.domain.Domain.models.responses.FirebaseCallback
import com.example.domain.Domain.models.responses.ResponseListFriendMaster
import com.example.domain.Domain.usecase.GetListFriendUseCase

class FriendActivityViewModel(
    private val getUserFriendListUSeCase: GetListFriendUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<ArrayList<ClientInform>>()
    val resultLive: MutableLiveData<ArrayList<ClientInform>> = resultLiveMutable

    fun getFriendList() {
        getUserFriendListUSeCase.execute(object : FirebaseCallback<ResponseListFriendMaster> {
            override fun onResponse(response: ResponseListFriendMaster) {
                resultLiveMutable.value = response.answer
            }
        })
    }

}