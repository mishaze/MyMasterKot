package com.example.mymaster.di

import com.example.data.data.repository.*
import com.example.data.data.storage.DataBase.*
import com.example.data.data.storage.interfaces.*
import com.example.data.data.storage.interfaces.UserInformationStorage
import com.example.domain.Domain.repository.*
import org.koin.dsl.module

val dataModUle = module {


    //MyProfile
    factory <UserInformationStorage> {
        com.example.data.data.storage.DataBase.UserInformationStorage()
    }

    factory<UserInformationRepository> {
        UserInformationRepositoryImpl(get())
    }

    //AddInFriend and FriendList
    factory<FriendStorage> {
        UserFriend()
    }

    factory<FriendRepository> {
        MasterFriendRepositoryImpl(get())
    }

    //ServicesList
    factory<ServicesStorage> {
        UserServices()
    }

    factory<ServicesRepository> {
        ServicesListRepositoryImpl(get())
    }
    //Schedule
    factory<ScheduleListStorage> {
        UserScheduleList()
    }

    factory<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }
    //ScheduleSetting

    factory<ScheduleSettingStorage> {
        ScheduleSettingList()
    }

    factory<ScheduleSettingRepository> {
        ScheduleSettingRepositoryImpl(get())
    }

    //Setting
    factory<SettingStorage> {
        Setting()
    }

    factory<SettingRepository> {
        SettingRepositoryImpl(get())
    }
}