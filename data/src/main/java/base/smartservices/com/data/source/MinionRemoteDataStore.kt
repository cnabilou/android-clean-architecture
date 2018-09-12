package base.smartservices.com.data.source

import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.data.repository.MinionDataStore
import base.smartservices.com.data.repository.MinionRemote
import io.reactivex.Completable
import io.reactivex.Single
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import javax.inject.Inject

/**
 * Implementation of the [MinionCacheDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class MinionRemoteDataStore @Inject constructor(private val minionRemote: MinionRemote): MinionDataStore {

    /**
     * Save a given Minion instance to the remote data source
     */
    override fun saveMinion(minion: MinionEntity): Completable {
        throw NotImplementedException()
    }

    /**
     * Clear all [MinionEntity] from the remote data source
     */
    override fun clearMinions(): Completable {
        throw NotImplementedException()
    }

    /**
     * Save a given [List] of [MinionEntity] to the remote data source
     */
    override fun saveMinions(minions: List<MinionEntity>): Completable {
        throw NotImplementedException()
    }

    /**
     * Retrieve a list of [MinionEntity] instance from the remote data source
     */
    override fun getMinions(): Single<List<MinionEntity>> {
        return minionRemote.getMinions()
    }
}