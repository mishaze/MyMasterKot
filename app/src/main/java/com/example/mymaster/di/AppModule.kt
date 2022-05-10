package com.example.mymaster.di

import com.example.mymaster.presentations.addFriendActivity.AddFriend
import com.example.mymaster.presentations.addFriendActivity.AddFriendActivityViewModel
import com.example.mymaster.presentations.friendActivity.FriendActivity
import com.example.mymaster.presentations.friendActivity.FriendActivityViewModel
import com.example.mymaster.presentations.myProfileActivity.MyProfileViewModel
import com.example.mymaster.presentations.servicesListActivity.ServiceListActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MyProfileViewModel> {
        MyProfileViewModel(
            getUserProfileUseCase = get(),
            setUserProfileUseCase = get()
        )
    }

    viewModel<AddFriendActivityViewModel> {
        AddFriendActivityViewModel(
            addFriendUseCase = get()
        )
    }

    viewModel<FriendActivityViewModel>() {
        FriendActivityViewModel(
            getUserFriendListUSeCase = get()
        )
    }

    viewModel<ServiceListActivityViewModel>() {
        ServiceListActivityViewModel(
            setUserServicesListUseCase = get(),
            getUserServicesListUseCase = get()
        )
    }
}