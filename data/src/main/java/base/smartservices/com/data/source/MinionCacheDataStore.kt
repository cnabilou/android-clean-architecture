package base.smartservices.com.data.source

import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.data.repository.MinionCache
import base.smartservices.com.data.repository.MinionDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [MinionDataStore] interface to provide a means of communicating
 * with the local data source
 */
open class MinionCacheDataStore @Inject constructor(
    private val minionCache: MinionCache): MinionDataStore {

    /**
     * Clear all the minions from the cache
     */
    override fun clearMinions(): Completable {
        return minionCache.clearMinions()
    }

    /**
     * Save a given [List] of [MinionEntity] instances to the cache
     */
    override fun saveMinions(minions: List<MinionEntity>): Completable {
        return minionCache.saveMinions(minions)
    }

    /**
     * Retrieve a list of [MinionEntity] instance from the cache.
     */
    override fun getMinions(): Single<List<MinionEntity>> {
        return minionCache.getMinions()
    }
}