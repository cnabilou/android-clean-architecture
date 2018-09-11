package com.smartservices.com.baseapp.injection.module

import com.smartservices.com.baseapp.browse.BrowseActivity
import com.smartservices.com.baseapp.injection.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [BrowseActivityModule::class])
    abstract fun bindMainActivity(): BrowseActivity
}