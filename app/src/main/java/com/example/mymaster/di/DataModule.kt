package com.example.mymaster.di

import com.example.data.data.repository.*
import com.example.data.data.storage.SharedPrefs.*
import com.example.data.data.storage.interfaces.*
import com.example.domain.Domain.repository.*
import org.koin.dsl.module

val dataModUle = module {


    //MyProfile
    factory <UserInformationStorage> {
        SharedPrefUserInformationStorage()
    }

    factory<UserInformationRepository> {
        UserInformationRepositoryImpl(get())
    }

    //AddInFriend and FriendList
    factory<FriendStorage> {
        SharedPrefUserFriend()
    }

    factory<FriendRepository> {
        MasterFriendRepositoryImpl(get())
    }

    //ServicesList
    factory<ServicesStorage> {
        SharedPrefsUserServices()
    }

    factory<ServicesRepository> {
        ServicesListRepositoryImpl(get())
    }
    //Schedule
    factory<ScheduleListStorage> {
        SharedPrefsUserScheduleList()
    }

    factory<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }
    //ScheduleSetting

    factory<ScheduleSettingStorage> {
        SharedPrefsScheduleSettingList()
    }

    factory<ScheduleSettingRepository> {
        ScheduleSettingRepositoryImpl(get())
    }

    //Setting
    factory<SettingStorage> {
        SharedPrefsSetting()
    }

    factory<SettingRepository> {
        SettingRepositoryImpl(get())
    }
}