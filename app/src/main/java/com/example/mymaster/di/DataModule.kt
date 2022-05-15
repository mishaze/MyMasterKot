package com.example.mymaster.di

import com.example.data.data.repository.*
import com.example.data.data.storage.SharedPrefs.*
import com.example.data.data.storage.interfaces.*
import com.example.domain.Domain.repository.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module
import java.util.*


val dataModUle = module {


    //MyProfile
    val db = FirebaseDatabase.getInstance()
        .getReference("Master")
        .child(
            FirebaseAuth.getInstance()
                .currentUser?.uid.toString()
        ).child("Information")


    //AddFriend and //ListFriend
    val mDb = FirebaseDatabase.getInstance()
        .getReference("Master")
        .child(
            FirebaseAuth.getInstance()
                .currentUser?.uid.toString()
        )

    //ServiceList
    val mDatabase  = FirebaseDatabase.getInstance()
    .getReference("Master")
    .child(
        FirebaseAuth.getInstance()
            .currentUser?.uid.toString()
    )
    //.child("Services")


    //MyProfile
    factory <UserInformationStorage> {
        SharedPrefUserInformationStorage(db!!)
    }

    factory<UserInformationRepository> {
        UserInformationRepositoryImpl(get())
    }

    //AddInFriend and  //Friend List
    factory<FriendStorage> {
        SharedPrefUserFriend(mDb)
    }

    factory<FriendRepository> {
        MasterFriendRepositoryImpl(get())
    }

    //ServicesList
    factory<ServicesStorage> {
        SharedPrefsUserServices(mDatabase)
    }

    factory<ServicesRepository> {
        ServicesListRepositoryImpl(get())
    }
    //Schedule
    factory<ScheduleListStorage> {
        SharedPrefsUserScheduleList(mDb)
    }

    factory<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }
    //ScheduleSetting

    factory<ScheduleSettingStorage> {
        SharedPrefsScheduleSettingList(mDatabase)
    }

    factory<ScheduleSettingRepository> {
        ScheduleSettingRepositoryImpl(get())
    }

}