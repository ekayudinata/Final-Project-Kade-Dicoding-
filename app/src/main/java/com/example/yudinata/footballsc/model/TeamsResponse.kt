package com.example.yudinata.footballsc.model

import com.google.gson.annotations.SerializedName

data class TeamsResponse (

        @field:SerializedName("teams")
        val teams: List<TeamsItem>
)