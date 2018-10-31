package com.example.yudinata.footballsc.feature.detail

import com.example.yudinata.footballsc.model.DetailItem
import com.example.yudinata.footballsc.model.Match

interface DetailView {
    fun showTeamDetails(dataHomeTeam: List<DetailItem>, dataAwayTeam: List<DetailItem>)
    fun showMatchDetail(dataMatch:List<Match>)
}