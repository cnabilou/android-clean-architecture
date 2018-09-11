package base.smartservices.com.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model used solely for the caching of a Minion
 */
@Entity(tableName = "minion")
data class CachedMinion(val name: String, val title: String, val avatar: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}