package base.smartservices.com.cache.mapper

import base.smartservices.com.cache.model.CachedMinion
import base.smartservices.com.data.model.MinionEntity
import javax.inject.Inject

/**
 * Map a [CachedMinion] instance to and from a [MinionEntity] instance when data is moving between
 * this layer and the Data layer
 */
class MinionEntityMapper @Inject constructor(): EntityMapper<CachedMinion, MinionEntity> {

    /**
     * Map a [MinionEntity] instance to a [CachedMinion] instance.
     */
    override fun mapToCached(type: MinionEntity): CachedMinion {
        return CachedMinion(type.name, type.title, type.avatar)
    }

    /**
     * Map a [CachedMinion] instance to a [MinionEntity] instance.
     */
    override fun mapFromCached(type: CachedMinion): MinionEntity {
        return MinionEntity(type.name, type.title, type.avatar)
    }
}