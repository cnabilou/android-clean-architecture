package base.smartservices.com.domain.repository

import base.smartservices.com.domain.model.Minion
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the operations that
 * need to be implemented.
 */
interface MinionRepository {
    fun clearMinions(): Completable
    fun saveMinions(minions: List<Minion>): Completable
    fun getMinions(): Single<List<Minion>>
}