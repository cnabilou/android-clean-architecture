package base.smartservices.com.data.repository

import base.smartservices.com.data.model.MinionEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Minions. This is to be implemented by the
 * Cache layer, using this interface as a way of communication.
 */
interface MinionCache {

    /**
     * Clear all Minions from the cache
     */
    fun clearMinions(): Completable

    /**
     * Save a given list of Minions to the cache
     */
    fun saveMinions(minions: List<MinionEntity>): Completable

    /**
     * Retrieve a list of Minions, from the cache
     */
    fun getMinions(): Single<List<MinionEntity>>

    /**
     * Check if an element exists in the cache.
     */
    fun isCached(): Boolean

    /**
     * Sets the cache time
     */
    fun setLastCacheTime(lastCache: Long)

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    fun isExpired(): Boolean
}