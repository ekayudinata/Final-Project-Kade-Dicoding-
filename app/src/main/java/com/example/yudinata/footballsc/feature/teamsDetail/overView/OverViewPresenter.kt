package com.example.yudinata.footballsc.feature.teamsDetail.overView

import com.example.yudinata.footballsc.model.TeamsResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class OverViewPresenter(private val view: OverView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLookupTeam(id: String?) {
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLookupTeam(id)),
                        TeamsResponse::class.java)
            }
            view.showOverView(data.await().teams)
        }
    }
}