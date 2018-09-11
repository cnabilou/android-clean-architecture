package com.smartservices.com.baseapp.injection.module

import base.smartservices.com.domain.interactor.browse.GetMinionsInteractor
import base.smartservices.com.presentation.browse.BrowseMinionsContract
import base.smartservices.com.presentation.browse.BrowseMinionsPresenter
import base.smartservices.com.presentation.mapper.MinionMapper
import com.smartservices.com.baseapp.browse.BrowseActivity
import com.smartservices.com.baseapp.injection.scopes.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an activity-level
 */
@Module
class BrowseActivityModule {

    @PerActivity
    @Provides
    internal fun provideBrowseView(browseActivity: BrowseActivity): BrowseMinionsContract.View {
        return browseActivity
    }

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(mainView: BrowseMinionsContract.View,
                                        getMinions: GetMinionsInteractor,
                                        mapper: MinionMapper): BrowseMinionsContract.Presenter {
        return BrowseMinionsPresenter(mainView, getMinions, mapper)
    }
}