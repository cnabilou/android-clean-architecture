package base.smartservices.com.presentation.browse

import base.smartservices.com.domain.interactor.CompletableUseCase
import base.smartservices.com.domain.interactor.SingleUseCase
import base.smartservices.com.domain.model.Minion
import base.smartservices.com.presentation.mapper.MinionMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class BrowseMinionsPresenter @Inject constructor(
    val browseView: BrowseMinionsContract.View,
    private val getMinionsUseCase: SingleUseCase<List<Minion>, Void>,
    private val saveMinionUseCase: CompletableUseCase<Minion>,
    private val minionMapper: MinionMapper): BrowseMinionsContract.Presenter {

    /**
     * Initialization
     */
    init {
        browseView.setPresenter(this)
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
     * Execute the request made on the use case
     */
    override fun retrieveMinions() {
        getMinionsUseCase.execute(MinionSubscriber())
    }

    /**
     * Execute the request on the use case to save the [Minion]
     */
    override fun saveMinion(minion: Minion) {
        saveMinionUseCase.execute(minion).doOnComplete { browseView.savedMinion() }
    }

    /**
     * Handles the GetMinion on success
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
     * Subscribe to the GetMinion Single Observer
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