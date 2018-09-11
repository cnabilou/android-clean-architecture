package com.smartservices.com.baseapp.injection.module

import android.app.Application
import android.content.Context
import base.smartservices.com.cache.BuildConfig
import base.smartservices.com.cache.MinionCacheImpl
import base.smartservices.com.cache.PreferencesHelper
import base.smartservices.com.data.MinionDataRepository
import base.smartservices.com.data.executor.JobExecutor
import base.smartservices.com.data.repository.MinionCache
import base.smartservices.com.data.repository.MinionRemote
import base.smartservices.com.data.source.MinionDataStoreFactory
import base.smartservices.com.domain.executor.PostExecutionThread
import base.smartservices.com.domain.executor.ThreadExecutor
import base.smartservices.com.domain.repository.MinionRepository
import base.smartservices.com.remote.MinionRemoteImpl
import base.smartservices.com.remote.MinionService
import base.smartservices.com.remote.MinionServiceFactory
import base.smartservices.com.remote.mapper.MinionEntityMapper
import com.smartservices.com.baseapp.UiThread
import com.smartservices.com.baseapp.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

/**
 * Module used to provide dependencies at an application-level
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @PerApplication
    internal fun provideMinionRepository(factory: MinionDataStoreFactory,
                                         mapper: base.smartservices.com.data.mapper.MinionMapper): MinionRepository {
        return MinionDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideMinionCache(context: Context,
                                    entityMapper: base.smartservices.com.cache.mapper.MinionEntityMapper,
                                    helper: PreferencesHelper): MinionCache {
        return MinionCacheImpl(context, entityMapper, helper)

    }

    @Provides
    @PerApplication
    internal fun provideMinionRemote(service: MinionService,
                                       mapper: MinionEntityMapper): MinionRemote {
        return MinionRemoteImpl(service, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideMinionService(): MinionService {
        return MinionServiceFactory.makeMinionService(BuildConfig.DEBUG)
    }
}