package com.example.yudinata.footballsc.model

import com.google.gson.annotations.SerializedName

data class PlayersResponse(

        @field:SerializedName("players")
        val players: List<PlayersItem>
)