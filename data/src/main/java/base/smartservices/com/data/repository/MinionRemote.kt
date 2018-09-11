package base.smartservices.com.data.repository

import base.smartservices.com.data.model.MinionEntity
import io.reactivex.Single

/**
 * Interface defining methods for the caching of Minions. This is to be implemented by the cache layer,
 * using this interface as a way of communication
 */
interface MinionRemote {
    /**
     * Retrieve a list Minions, from the cache
     */
    fun getMinions(): Single<List<MinionEntity>>
}