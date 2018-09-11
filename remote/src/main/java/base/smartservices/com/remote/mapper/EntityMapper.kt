package base.smartservices.com.remote.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <R> the remote model input type
 * @param <E> the entity model output type
 */
interface EntityMapper<in R, out E> {
    fun mapFromRemote(type: R): E
}