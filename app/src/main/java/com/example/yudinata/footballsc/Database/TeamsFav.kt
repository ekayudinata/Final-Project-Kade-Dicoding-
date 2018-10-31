package com.example.yudinata.footballsc.Database

data class TeamsFav(
        val id:Long,
        val idTeam: String?,
        val strTeam: String?,
        val strTeamBadge: String?,
        val intFormedYear:String?,
        val strStadium:String?
){
    companion object {
        const val TABLE_TEAM:String ="TABLE_TEAM"
        const val ID:String = "ID"

        const val TEAM_ID:String ="TEAM_ID"
        const val TEAM_NAME:String ="TEAM_NAME"
        const val TEAM_BADGE:String = "TEAM_BADGE"
        const val TEAM_FORMED:String = "TEAM_FORMED"
        const val TEAM_STADIUM:String = "TEAM_STADIUM"
    }
}