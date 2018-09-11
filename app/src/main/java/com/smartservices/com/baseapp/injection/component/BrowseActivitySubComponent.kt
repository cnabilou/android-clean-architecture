package com.smartservices.com.baseapp.injection.component

import com.smartservices.com.baseapp.browse.BrowseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector

@Subcomponent
interface BrowseActivitySubComponent: AndroidInjector<BrowseActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<BrowseActivity>()
}