package com.example.mymaster

import android.app.Application
import com.example.domain.Domain.models.responses.ResponseScheduleList
import com.example.mymaster.di.appModule
import com.example.mymaster.di.dataModUle
import com.example.mymaster.di.domainModule
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModUle))
        }


    }
}
