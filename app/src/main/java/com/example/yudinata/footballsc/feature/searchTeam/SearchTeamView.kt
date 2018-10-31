package com.example.yudinata.footballsc.feature.searchTeam

import com.example.yudinata.footballsc.model.TeamsItem

interface SearchTeamView {
    fun showEventList(data:List<TeamsItem>)
}