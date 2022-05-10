package com.example.domain.Domain.models.responses

interface FirebaseCallback<in T > {
    fun onResponse(response: T)
}