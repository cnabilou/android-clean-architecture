package base.smartservices.com.data.source

import base.smartservices.com.data.repository.MinionCache
import base.smartservices.com.data.repository.MinionDataStore
import javax.inject.Inject

/**
 * Create an instance of a MinionDataStore
 */
open class MinionDataStoreFactory @Inject constructor(
    private val minionCache: MinionCache,
    private val minionCacheDataStore: MinionCacheDataStore,
    private val minionRemoteDataStore: MinionRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(): MinionDataStore {
        if(minionCache.isCached() && !minionCache.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): MinionDataStore {
        return minionRemoteDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): MinionDataStore {
        return minionCacheDataStore
    }
}