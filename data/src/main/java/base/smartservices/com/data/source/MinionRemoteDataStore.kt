package base.smartservices.com.data.source

import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.data.repository.MinionDataStore
import base.smartservices.com.data.repository.MinionRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of the [MinionCacheDataStore] interface to provide a means of communicating
 * with the remote data source
 */
open class MinionRemoteDataStore @Inject constructor(private val minionRemote: MinionRemote): MinionDataStore {

    override fun clearMinions(): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveMinions(minions: List<MinionEntity>): Completable {
        throw UnsupportedOperationException()
    }

    /**
     * Retrieve a list of [MinionEntity] instance from the API
     */
    override fun getMinions(): Single<List<MinionEntity>> {
        return minionRemote.getMinions()
    }
}