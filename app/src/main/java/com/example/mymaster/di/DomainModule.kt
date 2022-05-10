package com.example.mymaster.di

import AddInFriendUseCase
import com.example.domain.Domain.usecase.*
import org.koin.dsl.module


val domainModule = module {

    //MyProfile
    factory<GetUserProfileUseCase> {
        GetUserProfileUseCase(get())
    }

    factory<SetUserProfileUseCase> {
        SetUserProfileUseCase(get())
    }

    //AddFriend
    factory<AddInFriendUseCase> {
        AddInFriendUseCase(get())
    }

    //GetListFriend
    factory<GetListFriendUseCase> {
        GetListFriendUseCase(get())
    }

    //ListServices
    factory<GetUserServicesListUseCase> {
        GetUserServicesListUseCase(get())
    }

    factory<SetUserServicesListUseCase> {
        SetUserServicesListUseCase(get())
    }

}