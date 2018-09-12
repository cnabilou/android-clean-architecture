package base.smartservices.com.data.repository

import base.smartservices.com.data.model.MinionEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Minions. This is to be implemented by the cache layer,
 * using this interface as a way of communication
 */
interface MinionRemote {
    /**
     * Retrieve a list Minions, from the remote data store
     */
    fun getMinions(): Single<List<MinionEntity>>

    /**
     * Save a given MinionModel to the remote data store
     */
    fun saveMinion(minion: MinionEntity): Completable

    /**
     * Clear all the minions from the remote data store
     */
    fun clearMinions(): Completable
}