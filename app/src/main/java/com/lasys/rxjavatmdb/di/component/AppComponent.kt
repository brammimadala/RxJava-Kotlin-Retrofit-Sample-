package com.lasys.rxjavatmdb.di.component

import android.app.Application
import android.content.Context
import com.lasys.rxjavatmdb.di.module.RetrofitInstanceModule
import com.lasys.rxjavatmdb.repository.MoviesRepository
import com.lasys.rxjavatmdb.view.MainActivity
import com.lasys.rxjavatmdb.viewmodel.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitInstanceModule::class]
)
interface AppComponent {


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(vm: MainActivityViewModel)


}