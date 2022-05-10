package com.example.mymaster.presentations.addFriendActivity

import AddInFriendUseCase
import androidx.lifecycle.ViewModel


class AddFriendActivityViewModel( private val addFriendUseCase: AddInFriendUseCase) : ViewModel(){


    fun addFriend(email :String)
    {
        addFriendUseCase.execute(email)
    }


}