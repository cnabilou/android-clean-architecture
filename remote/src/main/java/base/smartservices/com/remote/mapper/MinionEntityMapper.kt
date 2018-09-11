package base.smartservices.com.remote.mapper

import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.remote.model.MinionModel
import javax.inject.Inject

/**
 * Map a [MinionModel] to and from a [MinionEntity] instance when data is moving between
 * this later and the Data layer
 */
open class MinionEntityMapper @Inject constructor(): EntityMapper<MinionModel, MinionEntity> {

    /**
     * Map an instance of a [MinionModel] to a [MinionEntity] model
     */
    override fun mapFromRemote(type: MinionModel): MinionEntity {
        return MinionEntity(type.name, type.title, type.avatar)
    }
}