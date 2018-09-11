package base.smartservices.com.data.mapper

import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.domain.model.Minion
import javax.inject.Inject

/**
 * Map a [MinionEntity] to and from a [Minion] instance when data is moving between
 * this later and the Domain layer
 */
open class MinionMapper @Inject constructor(): Mapper<MinionEntity, Minion> {

    /**
     * Map a [MinionEntity] instance to a [Minion] instance
     */
    override fun mapFromEntity(type: MinionEntity): Minion {
        return Minion(type.name, type.title, type.avatar)
    }

    /**
     * Map a [Minion] instance to a [MinionEntity] instance
     */
    override fun mapToEntity(type: Minion): MinionEntity {
        return MinionEntity(type.name, type.title, type.avatar)
    }
}