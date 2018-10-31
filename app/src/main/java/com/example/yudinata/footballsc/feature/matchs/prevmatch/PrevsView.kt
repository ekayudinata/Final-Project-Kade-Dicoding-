package com.example.yudinata.footballsc.feature.matchs.prevmatch

import com.example.yudinata.footballsc.model.EventItem


interface PrevsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<EventItem>)
}