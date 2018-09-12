package base.smartservices.com.presentation.browse

import base.smartservices.com.domain.model.Minion
import base.smartservices.com.presentation.BasePresenter
import base.smartservices.com.presentation.BaseView
import base.smartservices.com.presentation.model.MinionView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseMinionsContract {
    interface View : BaseView<Presenter> {
        fun showProgress()
        fun hideProgress()
        fun showMinions(minions: List<MinionView>)
        fun savedMinion()
        fun hideMinions()
        fun showErrorState()
        fun hideErrorState()
        fun showEmptyState()
        fun hideEmptyState()
    }

    interface Presenter : BasePresenter {
        fun retrieveMinions()
        fun saveMinion(minion: Minion)
    }
}
