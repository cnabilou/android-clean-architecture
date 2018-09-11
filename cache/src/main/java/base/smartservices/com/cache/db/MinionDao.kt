package base.smartservices.com.cache.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import base.smartservices.com.cache.model.CachedMinion

@Dao
interface MinionDao {
    @Query("SELECT * FROM minion")
    fun getAll(): List<CachedMinion>

    @Query("DELETE FROM minion")
    fun deleteAll()

    @Insert
    fun saveMinion(minion: CachedMinion)

    @Insert
    fun saveMinions(minions: List<CachedMinion>)
}