package base.smartservices.com.domain.interactor.browse

import base.smartservices.com.domain.executor.PostExecutionThread
import base.smartservices.com.domain.executor.ThreadExecutor
import base.smartservices.com.domain.interactor.SingleUseCase
import base.smartservices.com.domain.model.Minion
import base.smartservices.com.domain.repository.MinionRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Minion] instances from the [MinionRepository]
 */
open class GetMinionsInteractor @Inject constructor(
    private val minionRepository: MinionRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread)
    : SingleUseCase<List<Minion>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Single<List<Minion>> {
        return minionRepository.getMinions()
    }
}