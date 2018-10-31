package com.example.yudinata.footballsc.feature.playerDetail


import com.example.yudinata.footballsc.model.PlayersResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailPresenter (val view: PlayerDetailView, private val context: CoroutineContextProvider = CoroutineContextProvider()){

    val apiRepository = ApiRepository()
    val gson = Gson()

    fun getPlayerDetails(id: String) {

        async(context.main){
            val dataplayer = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLookupPlayer(id)),
                        PlayersResponse::class.java)

            }
            view.showPlayerDetail(dataplayer.await().players)

        }
    }

}