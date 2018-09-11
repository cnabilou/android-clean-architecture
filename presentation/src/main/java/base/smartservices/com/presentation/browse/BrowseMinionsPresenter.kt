package base.smartservices.com.presentation.browse

import base.smartservices.com.domain.interactor.SingleUseCase
import base.smartservices.com.domain.model.Minion
import base.smartservices.com.presentation.mapper.MinionMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class BrowseMinionsPresenter @Inject constructor(
    val browseView: BrowseMinionsContract.View,
    private val getMinionsUseCase: SingleUseCase<List<Minion>, Void>,
    private val minionMapper: MinionMapper): BrowseMinionsContract.Presenter {

    /**
     * Init
     */
    init {
        browseView.setPresenter(this)
    }

    /**
     * Execute the request made on the use case
     */
    override fun retrieveMinions() {
        getMinionsUseCase.execute(MinionSubscriber())
    }

    /**
     * On start method
     */
    override fun start() {
        retrieveMinions()
    }

    /**
     *
     */
    override fun stop() {
        getMinionsUseCase.dispose()
    }

    /**
     *
     */
    internal fun handleGetMinionsSuccess(minions: List<Minion>) {
        browseView.hideErrorState()
        if(minions.isNotEmpty()) {
            browseView.hideEmptyState()
            browseView.hideProgress()
            browseView.showMinions(minions.map { minionMapper.mapToView(it) })
        } else {
            browseView.hideMinions()
            browseView.showEmptyState()
        }
    }

    /**
     *
     */
    inner class MinionSubscriber: DisposableSingleObserver<List<Minion>>() {
        override fun onSuccess(t: List<Minion>) {
            handleGetMinionsSuccess(t)
        }

        override fun onError(e: Throwable) {
            browseView.hideMinions()
            browseView.hideEmptyState()
            browseView.showErrorState()
        }
    }
}