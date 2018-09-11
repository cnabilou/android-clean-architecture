package com.smartservices.com.baseapp.injection

import android.app.Application
import com.smartservices.com.baseapp.MinionApplication
import com.smartservices.com.baseapp.injection.module.ActivityBindingModule
import com.smartservices.com.baseapp.injection.module.ApplicationModule
import com.smartservices.com.baseapp.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [ActivityBindingModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: MinionApplication)
}