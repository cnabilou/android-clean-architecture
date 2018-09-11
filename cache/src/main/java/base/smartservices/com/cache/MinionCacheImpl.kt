package base.smartservices.com.cache

import android.content.Context
import android.util.Log
import base.smartservices.com.cache.db.MinionDatabase
import base.smartservices.com.cache.mapper.MinionEntityMapper
import base.smartservices.com.cache.model.CachedMinion
import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.data.repository.MinionCache
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Cached implementation for retrieving and saving Minion instances. This class implements the
 * [MinionCache] from the Data Layer as it is that layer responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class MinionCacheImpl @Inject constructor(
    context: Context,
    private val minionEntityMapper: MinionEntityMapper,
    private val preferencesHelper: PreferencesHelper): MinionCache {

    private val EXPIRATION_TIME = 1.toLong() //(60 * 10 * 1000).toLong()
    private val database = MinionDatabase.getInstance(context)

    /**
     * Retrieve an instance from the database, used for tests
     */
    internal fun getDatabase(): MinionDatabase {
        return database
    }

    override fun clearMinions(): Completable {
        return Completable.defer {
            database.minionDao().deleteAll()
            Completable.complete()
        }
    }

    override fun saveMinions(minions: List<MinionEntity>): Completable {
        return Completable.defer {
            minions.forEach {
                saveMinion(minionEntityMapper.mapToCached(it))
            }
                Completable.complete()
        }
    }

    override fun getMinions(): Single<List<MinionEntity>> {
        return Single.defer<List<MinionEntity>> {
            val minionEntities = database.minionDao().getAll().map {
                minionEntityMapper.mapFromCached(it)
            }
            Single.just<List<MinionEntity>>(minionEntities)
        }
    }

    override fun isCached(): Boolean {
        var isCached = false
        ioThread {
            isCached = database.minionDao().getAll().count() > 0
        }
        return isCached

    }

    override fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    private fun saveMinion(cachedMinion: CachedMinion) {
        ioThread {
            database.minionDao().saveMinion(cachedMinion)
        }
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }
}