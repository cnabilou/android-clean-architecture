package base.smartservices.com.remote

import base.smartservices.com.data.model.MinionEntity
import base.smartservices.com.data.repository.MinionRemote
import base.smartservices.com.remote.mapper.MinionEntityMapper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote implementation for retrieving Minion instances. This class implements the
 * [MinionRemote] from the Data layer as it is that layer responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class MinionRemoteImpl @Inject constructor(private val minionService: MinionService,
                                           private val entityMapper: MinionEntityMapper): MinionRemote {

    /**
     * Retrieve a list of [MinionEntity] instnace from the [MinionService].
     */
    override fun getMinions(): Single<List<MinionEntity>> {
        return minionService.getMinions()
            .map {
                it.team.map { listItem ->
                    entityMapper.mapFromRemote(listItem)
                }
            }
    }
}