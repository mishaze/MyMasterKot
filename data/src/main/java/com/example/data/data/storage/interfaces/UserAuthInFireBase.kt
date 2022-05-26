package com.example.data.data.storage.interfaces


interface  UserAuthInFireBase {
    suspend fun sigInUser(email:String, password: String)

    suspend fun register(email:String, password: String, name: String)

    fun getFirebaseAuthState()

}