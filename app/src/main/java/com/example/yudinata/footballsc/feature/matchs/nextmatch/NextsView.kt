package com.example.yudinata.footballsc.feature.matchs.nextmatch

import com.example.yudinata.footballsc.model.EventItem

interface NextsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<EventItem>)
}