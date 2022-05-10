package com.example.data.data.storage.interfaces

import com.example.domain.Domain.usecase.CreateUserAccountWithEmail
import kotlinx.coroutines.flow.Flow

interface  UserAuthInFireBase {
    suspend fun sigInUser(email:String, password: String)

    suspend fun register(email:String, password: String, name: String)

    fun getFirebaseAuthState()

}