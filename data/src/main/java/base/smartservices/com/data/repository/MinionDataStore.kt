package base.smartservices.com.data.repository

import base.smartservices.com.data.model.MinionEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to Minions.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented.
 */
interface MinionDataStore {
    fun clearMinions(): Completable
    fun saveMinions(minions: List<MinionEntity>): Completable
    fun getMinions(): Single<List<MinionEntity>>
}