package com.example.mymaster.di

import com.example.data.data.repository.MasterFriendRepositoryImpl
import com.example.data.data.repository.ServicesListRepositoryImpl
import com.example.data.data.repository.UserInformationRepositoryImpl
import com.example.data.data.storage.interfaces.FriendStorage
import com.example.data.data.storage.SharedPrefs.SharedPrefUserFriend
import com.example.data.data.storage.SharedPrefs.SharedPrefUserInformationStorage
import com.example.data.data.storage.SharedPrefs.SharedPrefsUserServices
import com.example.data.data.storage.interfaces.ServicesStorage
import com.example.data.data.storage.interfaces.UserInformationStorage
import com.example.domain.Domain.repository.FriendRepository
import com.example.domain.Domain.repository.ServicesRepository
import com.example.domain.Domain.repository.UserInformationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module
import java.util.*


val dataModUle = module {


    //MyProfile
    val db = FirebaseDatabase
        .getInstance()
        .getReference("Master")
        .child(FirebaseAuth.getInstance()
            .currentUser!!.uid)
        .child("Information")

    //AddFriend and //ListFriend
    val mDb = FirebaseDatabase.getInstance()
        .getReference("Masters")
        .child(FirebaseAuth.getInstance()
        .currentUser!!.uid)

    //ServiceList
    val mDatabase = FirebaseDatabase.getInstance().getReference("Masters").child(
        Objects.requireNonNull(
            FirebaseAuth.getInstance().currentUser
        )!!.uid
    ).child("list_services")

    //MyProfile
    single<UserInformationStorage> {
        SharedPrefUserInformationStorage(db)
    }

    single<UserInformationRepository> {
        UserInformationRepositoryImpl(get())
    }

    //AddInFriend and  //Friend List
    single<FriendStorage> {
        SharedPrefUserFriend(mDb)
    }

    single<FriendRepository> {
        MasterFriendRepositoryImpl(get())
    }

    //ServicesList
    single<ServicesStorage> {
        SharedPrefsUserServices(mDatabase)
    }

    single<ServicesRepository> {
        ServicesListRepositoryImpl(get())
    }

}