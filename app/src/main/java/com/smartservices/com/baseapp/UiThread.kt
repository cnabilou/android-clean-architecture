package com.smartservices.com.baseapp

import base.smartservices.com.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * MainThread (UI Thread) implementation base on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
class UiThread @Inject internal constructor(): PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}