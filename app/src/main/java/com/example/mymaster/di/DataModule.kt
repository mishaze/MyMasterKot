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
    val db = FirebaseDatabase
        .getInstance()
        .getReference("Master")
        .child(FirebaseAuth.getInstance()
            .currentUser!!.uid)
        .child("Information")

    //AddFriend and //ListFriend
    val mDb = FirebaseDatabase.getInstance()
        .getReference("Master")
        .child(FirebaseAuth.getInstance()
        .currentUser!!.uid)

    //ServiceList
    val mDatabase  = FirebaseDatabase.getInstance()
        .getReference("Master")
        .child(FirebaseAuth.getInstance()
            .currentUser!!.uid)
    //.child("Services")


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
    //Schedule
    single<ScheduleListStorage> {
        SharedPrefsUserScheduleList(mDb)
    }

    single<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }
    //ScheduleSetting

    single<ScheduleSettingStorage> {
        SharedPrefsScheduleSettingList(mDatabase)
    }

    single<ScheduleSettingRepository> {
        ScheduleSettingRepositoryImpl(get())
    }

}