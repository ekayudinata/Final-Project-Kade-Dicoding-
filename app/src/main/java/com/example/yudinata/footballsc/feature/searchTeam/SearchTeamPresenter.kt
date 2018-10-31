package com.example.yudinata.footballsc.feature.searchTeam

import com.example.yudinata.footballsc.model.TeamsResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter (val view  : SearchTeamView) {

    val apiRepository = ApiRepository()
    val gson = Gson()
    fun  getMatchSearch(Team: String?) {
        async(UI){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getSearchTeam(Team)),
                        TeamsResponse::class.java)
            }
            view.showEventList(data.await().teams)
        }
    }
}