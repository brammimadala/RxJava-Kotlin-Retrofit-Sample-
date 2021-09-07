package com.lasys.rxjavatmdb.di

import android.app.Application
import com.lasys.rxjavatmdb.di.component.AppComponent
import com.lasys.rxjavatmdb.di.component.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

}