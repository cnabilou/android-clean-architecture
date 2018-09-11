package base.smartservices.com.presentation.mapper

import base.smartservices.com.domain.model.Minion
import base.smartservices.com.presentation.model.MinionView
import javax.inject.Inject

/**
 * Map a [MinionView] to and from a [Minion] instance when data is moving between
 * this layer and the Domain layer
 */
open class MinionMapper @Inject constructor(): Mapper<MinionView, Minion> {
    override fun mapToView(type: Minion): MinionView {
        return MinionView(type.name, type.title, type.avatar)
    }
}