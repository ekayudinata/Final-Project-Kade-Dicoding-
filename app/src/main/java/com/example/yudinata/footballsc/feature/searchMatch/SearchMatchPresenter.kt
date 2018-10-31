package com.example.yudinata.footballsc.feature.searchMatch

import com.example.yudinata.footballsc.model.SearchMatchResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.experimental.android.UI

class SearchMatchPresenter(val view  : SearchMatchView) {

    val apiRepository = ApiRepository()
    val gson = Gson()
    fun  getMatchSearch(match: String?) {
        async(UI){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getSearchMatch(match)),
                        SearchMatchResponse::class.java)
            }
            view.showEventList(data.await().event)

        }
    }
}

