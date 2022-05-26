package com.example.data.data.storage.SharedPrefs

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SharedPrefsGeo {
    fun setGeo(geo :String) {

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("Geo").setValue(geo)

    }

    fun setAddress(address :String) {

        FirebaseDatabase.getInstance()
            .getReference("Master")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("Information")
            .child("address")
            .setValue(address)

    }
}