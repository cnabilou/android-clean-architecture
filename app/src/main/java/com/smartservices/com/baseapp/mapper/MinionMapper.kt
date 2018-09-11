package com.smartservices.com.baseapp.mapper

import base.smartservices.com.presentation.model.MinionView
import com.smartservices.com.baseapp.model.MinionViewModel
import javax.inject.Inject

/**
 * Map a [MinionView] to and from a [MinionViewModel] instance when data is moving between
 * this layer and the Domain layer
 */
open class MinionMapper @Inject constructor(): Mapper<MinionViewModel, MinionView> {

    /**
     * Map a [MinionView] instance to a [MinionViewModel] instance
     */
    override fun mapToViewModel(type: MinionView): MinionViewModel {
        return MinionViewModel(type.name, type.title, type.avatar)
    }
}