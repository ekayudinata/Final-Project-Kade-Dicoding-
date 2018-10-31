package com.example.yudinata.footballsc.feature.teams

import com.example.yudinata.footballsc.model.TeamsItem

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamsItem>)
}