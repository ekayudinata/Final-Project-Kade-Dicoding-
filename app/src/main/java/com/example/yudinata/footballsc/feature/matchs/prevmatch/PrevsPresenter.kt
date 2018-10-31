package com.example.yudinata.footballsc.feature.matchs.prevmatch

import com.example.yudinata.footballsc.model.EventResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class PrevsPresenter(private val view: PrevsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(league: String?) {
        view.showLoading()

        async(context.main){
            val data =  bg{ gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPrevs(league)),
                    EventResponse::class.java
                 )
            }
            view.hideLoading()
            view.showTeamList(data.await().events)

        }
    }
}