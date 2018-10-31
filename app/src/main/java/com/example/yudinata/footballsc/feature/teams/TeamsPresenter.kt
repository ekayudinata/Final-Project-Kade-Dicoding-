package com.example.yudinata.footballsc.feature.teams

import com.example.yudinata.footballsc.model.EventResponse
import com.example.yudinata.footballsc.model.TeamsResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter (private val view: TeamsView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(id: String?) {
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeam(id)),
                        TeamsResponse::class.java)
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()

        }
    }
}