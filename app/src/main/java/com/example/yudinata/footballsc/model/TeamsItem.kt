package com.example.yudinata.footballsc.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class TeamsItem(

        @field:SerializedName("intStadiumCapacity")
        val intStadiumCapacity: String? = null,

        @field:SerializedName("strTeamShort")
        val strTeamShort: Any? = null,

        @field:SerializedName("strSport")
        val strSport: String? = null,

        @field:SerializedName("strDescriptionCN")
        val strDescriptionCN: Any? = null,

        @field:SerializedName("strTeamJersey")
        val strTeamJersey: String? = null,

        @field:SerializedName("strTeamFanart2")
        val strTeamFanart2: String? = null,

        @field:SerializedName("strTeamFanart3")
        val strTeamFanart3: String? = null,

        @field:SerializedName("strTeamFanart4")
        val strTeamFanart4: String? = null,

        @field:SerializedName("strStadiumDescription")
        val strStadiumDescription: String? = null,

        @field:SerializedName("strTeamFanart1")
        val strTeamFanart1: String? = null,

        @field:SerializedName("intLoved")
        val intLoved: Any? = null,

        @field:SerializedName("idLeague")
        val idLeague: String? = null,

        @field:SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,

        @field:SerializedName("strTeamLogo")
        val strTeamLogo: String? = null,

        @field:SerializedName("strDescriptionSE")
        val strDescriptionSE: Any? = null,

        @field:SerializedName("strDescriptionJP")
        val strDescriptionJP: Any? = null,

        @field:SerializedName("strDescriptionFR")
        val strDescriptionFR: Any? = null,

        @field:SerializedName("strStadiumLocation")
        val strStadiumLocation: String? = null,

        @field:SerializedName("strDescriptionNL")
        val strDescriptionNL: Any? = null,

        @field:SerializedName("strCountry")
        val strCountry: String? = null,

        @field:SerializedName("strRSS")
        val strRSS: String? = null,

        @field:SerializedName("strDescriptionRU")
        val strDescriptionRU: Any? = null,

        @field:SerializedName("strTeamBanner")
        val strTeamBanner: String? = null,

        @field:SerializedName("strDescriptionNO")
        val strDescriptionNO: Any? = null,

        @field:SerializedName("strStadiumThumb")
        val strStadiumThumb: String? = null,

        @field:SerializedName("strDescriptionES")
        val strDescriptionES: String? = null,

        @field:SerializedName("intFormedYear")
        val intFormedYear: String? = null,

        @field:SerializedName("strInstagram")
        val strInstagram: String? = null,

        @field:SerializedName("strDescriptionIT")
        val strDescriptionIT: Any? = null,

        @field:SerializedName("idTeam")
        val idTeam: String? = null,

        @field:SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,

        @field:SerializedName("strWebsite")
        val strWebsite: String? = null,

        @field:SerializedName("strYoutube")
        val strYoutube: String? = null,

        @field:SerializedName("strDescriptionIL")
        val strDescriptionIL: Any? = null,

        @field:SerializedName("strLocked")
        val strLocked: String? = null,

        @field:SerializedName("strAlternate")
        val strAlternate: String? = null,

        @field:SerializedName("strTeam")
        val strTeam: String? = null,

        @field:SerializedName("strTwitter")
        val strTwitter: String? = null,

        @field:SerializedName("strDescriptionHU")
        val strDescriptionHU: Any? = null,

        @field:SerializedName("strGender")
        val strGender: String? = null,

        @field:SerializedName("strDivision")
        val strDivision: Any? = null,

        @field:SerializedName("strStadium")
        val strStadium: String? = null,

        @field:SerializedName("strFacebook")
        val strFacebook: String? = null,

        @field:SerializedName("strTeamBadge")
        val strTeamBadge: String? = null,

        @field:SerializedName("strDescriptionPT")
        val strDescriptionPT: Any? = null,

        @field:SerializedName("strDescriptionDE")
        val strDescriptionDE: Any? = null,

        @field:SerializedName("strLeague")
        val strLeague: String? = null,

        @field:SerializedName("strManager")
        val strManager: String? = null,

        @field:SerializedName("strKeywords")
        val strKeywords: String? = null,

        @field:SerializedName("strDescriptionPL")
        val strDescriptionPL: Any? = null
)
//        : Parcelable {
//        constructor(parcel: Parcel) : this(
//                parcel.readString(),
//                TODO("strTeamShort"),
//                parcel.readString(),
//                TODO("strDescriptionCN"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("intLoved"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionSE"),
//                TODO("strDescriptionJP"),
//                TODO("strDescriptionFR"),
//                parcel.readString(),
//                TODO("strDescriptionNL"),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionRU"),
//                parcel.readString(),
//                TODO("strDescriptionNO"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionIT"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionIL"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionHU"),
//                parcel.readString(),
//                TODO("strDivision"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionPT"),
//                TODO("strDescriptionDE"),
//                parcel.readString(),
//                parcel.readString(),
//                parcel.readString(),
//                TODO("strDescriptionPL")) {
//        }
//
//        override fun writeToParcel(parcel: Parcel, flags: Int) {
//                parcel.writeString(intStadiumCapacity)
//                parcel.writeString(strSport)
//                parcel.writeString(strTeamJersey)
//                parcel.writeString(strTeamFanart2)
//                parcel.writeString(strTeamFanart3)
//                parcel.writeString(strTeamFanart4)
//                parcel.writeString(strStadiumDescription)
//                parcel.writeString(strTeamFanart1)
//                parcel.writeString(idLeague)
//                parcel.writeString(idSoccerXML)
//                parcel.writeString(strTeamLogo)
//                parcel.writeString(strStadiumLocation)
//                parcel.writeString(strCountry)
//                parcel.writeString(strRSS)
//                parcel.writeString(strTeamBanner)
//                parcel.writeString(strStadiumThumb)
//                parcel.writeString(strDescriptionES)
//                parcel.writeString(intFormedYear)
//                parcel.writeString(strInstagram)
//                parcel.writeString(idTeam)
//                parcel.writeString(strDescriptionEN)
//                parcel.writeString(strWebsite)
//                parcel.writeString(strYoutube)
//                parcel.writeString(strLocked)
//                parcel.writeString(strAlternate)
//                parcel.writeString(strTeam)
//                parcel.writeString(strTwitter)
//                parcel.writeString(strGender)
//                parcel.writeString(strStadium)
//                parcel.writeString(strFacebook)
//                parcel.writeString(strTeamBadge)
//                parcel.writeString(strLeague)
//                parcel.writeString(strManager)
//                parcel.writeString(strKeywords)
//        }
//
//        override fun describeContents(): Int {
//                return 0
//        }
//
//        companion object CREATOR : Parcelable.Creator<TeamsItem> {
//                override fun createFromParcel(parcel: Parcel): TeamsItem {
//                        return TeamsItem(parcel)
//                }
//
//                override fun newArray(size: Int): Array<TeamsItem?> {
//                        return arrayOfNulls(size)
//                }
//        }
//}