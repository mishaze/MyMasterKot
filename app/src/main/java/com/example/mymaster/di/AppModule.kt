package com.example.mymaster.di

import com.example.mymaster.presentations.Friend.FriendViewModel
import com.example.mymaster.presentations.MyProfile.MyProfileViewModel
import com.example.mymaster.presentations.Schedule.ScheduleViewModel
import com.example.mymaster.presentations.ScheduleSetting.ScheduleSettingViewModel
import com.example.mymaster.presentations.ServicesList.ServiceListViewModel
import com.example.mymaster.presentations.Settings.SettingViewModel
import com.example.mymaster.presentations.Stat.StatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MyProfileViewModel> {
        MyProfileViewModel(
            getUserProfileUseCase = get(),
            setUserProfileUseCase = get()
        )
    }


    viewModel<FriendViewModel>() {
        FriendViewModel(
            getUserFriendListUSeCase = get(),
            addFriendUseCase = get()
        )
    }

    viewModel<ServiceListViewModel>() {
        ServiceListViewModel(
            setUserServicesListUseCase = get(),
            getUserServicesListUseCase = get()
        )
    }
    viewModel<ScheduleSettingViewModel>() {
        ScheduleSettingViewModel(
            getUserScheduleSettingUseCase = get(),
            setUserScheduleSettingUseCase = get()
        )

    }
    viewModel<ScheduleViewModel>() {
        ScheduleViewModel(
            getUserScheduleListUSeCase = get()
        )
    }

    viewModel<SettingViewModel>() {
        SettingViewModel(
            getUserSettingUseCase = get(),
            setUserSettingUseCase = get()
        )
    }

    viewModel<StatViewModel>() {
        StatViewModel(
            getUserStat = get()
        )
    }

}