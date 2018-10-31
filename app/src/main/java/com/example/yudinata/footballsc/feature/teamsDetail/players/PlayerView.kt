package com.example.yudinata.footballsc.feature.teamsDetail.players

import com.example.yudinata.footballsc.model.EventItem
import com.example.yudinata.footballsc.model.PlayerItem

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<PlayerItem>)
}