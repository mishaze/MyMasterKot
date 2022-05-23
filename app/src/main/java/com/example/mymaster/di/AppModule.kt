package com.example.mymaster.di

import com.example.mymaster.presentations.addFriendActivity.AddFriendActivityViewModel
import com.example.mymaster.presentations.friend.FriendActivityViewModel
import com.example.mymaster.presentations.MyProfile.MyProfileViewModel
import com.example.mymaster.presentations.Schedule.ScheduleActivityViewModel
import com.example.mymaster.presentations.scheduleSettingActivity.FragmentScheduleSetting
import com.example.mymaster.presentations.scheduleSettingActivity.ScheduleSettingActivityViewModel
import com.example.mymaster.presentations.servicesList.ServiceListActivityViewModel
import com.example.mymaster.presentations.settingsActivity.SettingViewModel
import org.koin.androidx.fragment.dsl.fragment
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
            getUserFriendListUSeCase = get(),
            addFriendUseCase = get()
        )
    }

    viewModel<ServiceListActivityViewModel>() {
        ServiceListActivityViewModel(
            setUserServicesListUseCase = get(),
            getUserServicesListUseCase = get()
        )
    }
    viewModel<ScheduleSettingActivityViewModel>() {
        ScheduleSettingActivityViewModel(
            getUserScheduleSettingUseCase = get(),
            setUserScheduleSettingUseCase = get()
        )

    }
    viewModel<ScheduleActivityViewModel>() {
        ScheduleActivityViewModel(
            getUserScheduleListUSeCase = get()
        )
    }

    viewModel<SettingViewModel>() {
        SettingViewModel(
            getUserSettingUseCase = get(),
            setUserSettingUseCase= get()
        )
    }


}