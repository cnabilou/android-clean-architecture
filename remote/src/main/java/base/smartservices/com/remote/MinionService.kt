package base.smartservices.com.remote

import base.smartservices.com.remote.model.MinionModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the Minion API
 */
interface MinionService {
    @GET("/api/minion")
    fun getMinions(): Single<MinionReponse>

    class MinionReponse {
        lateinit var team: List<MinionModel>
    }
}