package base.smartservices.com.data

import base.smartservices.com.data.mapper.MinionMapper
import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.data.source.MinionDataStoreFactory
import base.smartservices.com.data.source.MinionRemoteDataStore
import base.smartservices.com.domain.model.Minion
import base.smartservices.com.domain.repository.MinionRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides an implementation of the [MinionDataRepository] interface for communicating
 * to and from data sources
 */
class MinionDataRepository @Inject constructor(
    private val factory: MinionDataStoreFactory,
    private val minionMapper: MinionMapper): MinionRepository {

    override fun clearMinions(): Completable {
        return factory.retrieveCacheDataStore().clearMinions()
    }

    override fun saveMinions(minions: List<Minion>): Completable {
        val minionEntities = minions.map { minionMapper.mapToEntity(it) }
        return saveMinionEntities(minionEntities)
    }

    override fun saveMinion(minion: Minion): Completable {
        val minionEntity = minionMapper.mapToEntity(minion)
        return saveMinionEntity(minionEntity)
    }

    override fun getMinions(): Single<List<Minion>> {
        // Calls the data and returns the appropriate Data Store
        val dataStore = factory.retrieveDataStore()

        return dataStore.getMinions()
            .flatMap {
                if(dataStore is MinionRemoteDataStore) {
                    saveMinionEntities(it).toSingle { it }
                } else {
                    Single.just(it)
                }
            }
            .map { list ->
                list.map{ listItem ->
                    minionMapper.mapFromEntity(listItem)
                }
            }
    }

    private fun saveMinionEntities(minions: List<MinionEntity>): Completable {
        return factory.retrieveCacheDataStore().saveMinions(minions)
    }

    private fun saveMinionEntity(minion: MinionEntity): Completable {
        return factory.retrieveCacheDataStore().saveMinion(minion)
    }

}