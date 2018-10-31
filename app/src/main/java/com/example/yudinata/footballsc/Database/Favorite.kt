package com.example.yudinata.footballsc.Database

data class Favorite(
        val id :Long,
        val idEvent: String?,
        val dateEvent: String?,
        val strTime:String?,
        //home
        val idHomeTeam: String?,
        val strHomeTeam: String?,
        val intHomeScore:String?,
        //away
        val idAwayTeam: String?,
        val strAwayTeam:String?,
        val intAwayScore: String?
        )
{
    companion object {
        const val TABLE_FAVORITE:String ="TABLE_FAVORITE"
        const val ID:String = "ID"

        const val EVENT_ID:String ="EVENT_ID"
        const val DATE_EVENT:String ="DATE_EVENT"
        const val TIME_EVENT:String ="TIME_EVENT"

        const val HOME_ID:String = "HOME_ID"
        const val HOME_TEAM:String ="HOME_TEAM"
        const val HOME_SCORE:String = "HOME_SCORE"


        const val AWAY_ID:String = "AWAY_ID"
        const val AWAY_TEAM:String ="AWAY_TEAM"
        const val AWAY_SCORE:String = "AWAY_SCORE"
    }
}
