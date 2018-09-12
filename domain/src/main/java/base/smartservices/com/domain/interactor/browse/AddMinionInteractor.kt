package base.smartservices.com.domain.interactor.browse

import base.smartservices.com.domain.executor.PostExecutionThread
import base.smartservices.com.domain.executor.ThreadExecutor
import base.smartservices.com.domain.interactor.CompletableUseCase
import base.smartservices.com.domain.interactor.SingleUseCase
import base.smartservices.com.domain.model.Minion
import base.smartservices.com.domain.repository.MinionRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case used for adding a new [Minion] to the [List] of minions from the [MinionRepository]
 */
open class AddMinionInteractor @Inject constructor(
    private val minionRepository: MinionRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread)
    : CompletableUseCase<Minion>(threadExecutor, postExecutionThread)
{
    override fun buildUseCaseObservable(params: Minion): Completable {
        return minionRepository.saveMinion(params)
    }

}