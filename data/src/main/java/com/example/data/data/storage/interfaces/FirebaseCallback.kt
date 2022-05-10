package com.example.data.data.storage.interfaces

interface FirebaseCallback <in T> {
    fun onResponse(response : T)
}