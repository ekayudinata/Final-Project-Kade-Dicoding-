package com.example.yudinata.footballsc.feature.detail

import com.example.yudinata.footballsc.model.DetailResponse
import com.example.yudinata.footballsc.model.MatchResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg


class MatchDetailPresenter(val view: DetailView, private val context: CoroutineContextProvider = CoroutineContextProvider()){

val apiRepository = ApiRepository()
val gson = Gson()

fun getTeamDetails(idHomeTeam: String, idAwayTeam: String) {

    async(context.main){
        val dataHomeTeam = bg{
            gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPhoto(idHomeTeam)),
                DetailResponse::class.java)

        }
        val dataAwayTeam = bg{
            gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPhoto(idAwayTeam)),
                    DetailResponse::class.java)

        }
        view.showTeamDetails(dataHomeTeam.await().teams!!,dataAwayTeam.await().teams!!)

    }
}

    fun getDetailEvent(idEvent:String){

        async(context.main){
            val dataEvent = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getEvent(idEvent)),
                        MatchResponse::class.java)

            }
            view.showMatchDetail(dataEvent.await().events)

        }
    }


}