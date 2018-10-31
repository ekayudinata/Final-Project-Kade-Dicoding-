package com.example.yudinata.footballsc.feature.teamsDetail.players

import com.example.yudinata.footballsc.feature.matchs.nextmatch.NextsView
import com.example.yudinata.footballsc.model.EventResponse
import com.example.yudinata.footballsc.model.PlayerResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter (private val view: PlayerView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getAllPlayer(id: String?) {
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLookupAllPlayer(id)),
                        PlayerResponse::class.java)
            }
            view.showTeamList(data.await().player)
            view.hideLoading()

        }
    }
}